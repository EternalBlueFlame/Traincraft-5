package fexcraft.fvtm;

import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.TexturedPolygon;
import fexcraft.tmt.slim.TexturedVertex;
import fexcraft.tmt.slim.Vec3f;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * All rights reserved. Only to be distributed within authorized mods.
 *
 * @author Ferdinand Calo' (FEX___96)
 */
public class BEOModelLoader {

	private static final int END = 0;
	private static final int NAME = 1;
	private static final int AUTHOR = 2;
	private static final int TEXSIZE = 3;
	private static final int GROUP = 4;
	private static final int OBJECT = 5;
	private static final int POSITION = 2;
	private static final int ROTATION = 3;
	private static final int VECTOR = 4;
	private static final int UV = 5;
	private static final int NORMAL = 6;
	private static final int FACE = 7;
	private static ArrayList<Vec3f> vecs = new ArrayList<>();
	private static ArrayList<float[]> uvs = new ArrayList<>();

	public static RollingStockModel load(String loc){
		try{
			return loadModel(loc);
		}
		catch(Exception e){
			e.printStackTrace();
			return new RollingStockModel();
		}
	}

	public static RollingStockModel loadModel(String loc) throws Exception {
		RollingStockModel model = new RollingStockModel();
		InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(loc)).getInputStream();
		//copy stream to exclude zip errors
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int read;
		while((read = stream.read(buffer)) != -1) out.write(buffer, 0, read);
		stream = new ByteArrayInputStream(out.toByteArray());
		//
		int f0 = stream.read(), f1 = stream.read(), f2 = stream.read(), format = stream.read();
		if(f0 != 6 || f1 != 2 || f2 != 15 || format < 0) return model;
		int r;
		while((r = stream.read()) > -1){
			switch(r){
				case NAME:{
					model.name = readString(stream);
					break;
				}
				case AUTHOR:{
					model.addToCreators(readString(stream));
					break;
				}
				case TEXSIZE:{
					int[] in = readIntegers(stream, 2);
					model.textureWidth = model.textureX = in[0];
					model.textureHeight = model.textureY = in[1];
					break;
				}
				case GROUP:{
					TurboList group = new TurboList(readString(stream));
					readPolygons(stream, group, model.textureX, model.textureY);
					model.groups.add(group);
					break;
				}
				default:
					break;
			}
		}
		//
		stream.close();
		vecs.clear();
		uvs.clear();
		return model;
	}

	private static byte[] read(InputStream stream) throws IOException{
		ArrayList<Byte> list = new ArrayList<>();
		while(true){
			int i = stream.read();
			if(i == END || i == -1) break;
			list.add((byte)i);
		}
		byte[] arr = new byte[list.size()];
		for(int i = 0; i < list.size(); i++) arr[i] = list.get(i);
		return arr;
	}

	private static String readString(InputStream stream) throws IOException {
		return new String(read(stream), StandardCharsets.UTF_8);
	}

	private static void readPolygons(InputStream stream, TurboList group, int tx, int ty) throws IOException {
		ModelRendererTurbo mrt;
		int r;
		while(true){
			if((r = stream.read()) == -1) break;
			if(r != OBJECT) break;
			mrt = new ModelRendererTurbo(group, 0, 0, tx, ty);
			while(true){
				if((r = stream.read()) == -1) break;
				if(r == END){
					group.add(mrt);
					break;
				}
				switch(r){
					case NAME:{
						mrt.boxName = readString(stream);
						continue;
					}
					case POSITION:{
						float[] fl = readFloats(stream, 3);
						mrt.setRotationPoint(fl[0], fl[1], fl[2]);
						continue;
					}
					case ROTATION:{
						float[] fl = readFloats(stream, 3);
						mrt.setRotationAngle(fl[0], fl[1], fl[2]);
						continue;
					}
					case VECTOR:{
						float[] fl = readFloats(stream, 3);
						vecs.add(new Vec3f(fl[0], fl[1], fl[2]));
						continue;
					}
					case UV:{
						uvs.add(readFloats(stream, 2));
						continue;
					}
					case FACE:{
						int len = readIntegers(stream, 1)[0];
						int[] ids = readIntegers(stream, len + len);
						TexturedVertex[] verts = new TexturedVertex[len];
						for(int i = 0; i < len; i++){
							Vec3f vec = vecs.get(ids[i]);
							float[] uv = uvs.get(ids[i + len]);
							verts[i] = new TexturedVertex(vec.xCoord, vec.yCoord, vec.zCoord, uv[0], uv[1]);
						}
						mrt.faces.add(new TexturedPolygon(verts));
						continue;
					}
					default: break;
				}
			}
		}
	}

	private static float[] readFloats(InputStream stream, int t) throws IOException {
		float[] arr = new float[t];
		for(int i = 0; i < t; i++){
			byte[] bit = new byte[4];
			int r = stream.read(bit);
			if(r < 0) return arr;//error
			arr[i] = ByteBuffer.wrap(bit).getFloat();
		}
		return arr;
	}

	private static int[] readIntegers(InputStream stream, int t) throws IOException {
		int[] arr = new int[t];
		for(int i = 0; i < t; i++){
			byte[] bit = new byte[4];
			int r = stream.read(bit);
			if(r < 0) return arr;//error
			arr[i] = ByteBuffer.wrap(bit).getInt();
		}
		return arr;
	}

}
