//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2023 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 
// Model Creator: 
// Created on: 18.06.2022 - 13:56:48
// Last changed on: 18.06.2022 - 13:56:48

package train.client.render.models; //Path where the model is located

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import fexcraft.tmt.slim.ModelConverter;
import fexcraft.tmt.slim.ModelRendererTurbo;
import train.common.api.Locomotive;

public class ModelDuewagGT6ZRLoco extends ModelConverter //Same as Filename
{
	int textureX = 512;
	int textureY = 256;

	public ModelDuewagGT6ZRLoco() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[379];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);


		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 0, 1, textureX, textureY); // Box 9
		bodyModel[1] = new ModelRendererTurbo(this, 41, 1, textureX, textureY); // Box 62
		bodyModel[2] = new ModelRendererTurbo(this, 161, 1, textureX, textureY); // Box 63
		bodyModel[3] = new ModelRendererTurbo(this, 193, 1, textureX, textureY); // Box 65
		bodyModel[4] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 66
		bodyModel[5] = new ModelRendererTurbo(this, 217, 1, textureX, textureY); // Box 67
		bodyModel[6] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 72
		bodyModel[7] = new ModelRendererTurbo(this, 233, 1, textureX, textureY); // Box 75
		bodyModel[8] = new ModelRendererTurbo(this, 329, 1, textureX, textureY); // Box 76
		bodyModel[9] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 77
		bodyModel[10] = new ModelRendererTurbo(this, 425, 1, textureX, textureY); // Box 76
		bodyModel[11] = new ModelRendererTurbo(this, 433, 1, textureX, textureY); // Box 79
		bodyModel[12] = new ModelRendererTurbo(this, 441, 1, textureX, textureY); // Box 80
		bodyModel[13] = new ModelRendererTurbo(this, 449, 1, textureX, textureY); // Box 81
		bodyModel[14] = new ModelRendererTurbo(this, 249, 9, textureX, textureY); // Box 140
		bodyModel[15] = new ModelRendererTurbo(this, 233, 17, textureX, textureY); // Box 142
		bodyModel[16] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // Box 145
		bodyModel[17] = new ModelRendererTurbo(this, 345, 9, textureX, textureY); // Box 154
		bodyModel[18] = new ModelRendererTurbo(this, 377, 9, textureX, textureY); // Box 165
		bodyModel[19] = new ModelRendererTurbo(this, 505, 1, textureX, textureY); // Box 184
		bodyModel[20] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Box 185
		bodyModel[21] = new ModelRendererTurbo(this, 217, 17, textureX, textureY); // Box 186
		bodyModel[22] = new ModelRendererTurbo(this, 9, 25, textureX, textureY); // Box 82
		bodyModel[23] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // Box 58
		bodyModel[24] = new ModelRendererTurbo(this, 17, 25, textureX, textureY); // Box 86
		bodyModel[25] = new ModelRendererTurbo(this, 41, 9, textureX, textureY); // Box 179
		bodyModel[26] = new ModelRendererTurbo(this, 25, 25, textureX, textureY); // Box 180
		bodyModel[27] = new ModelRendererTurbo(this, 33, 25, textureX, textureY); // Box 181
		bodyModel[28] = new ModelRendererTurbo(this, 377, 9, textureX, textureY); // Box 182
		bodyModel[29] = new ModelRendererTurbo(this, 457, 9, textureX, textureY); // Box 183
		bodyModel[30] = new ModelRendererTurbo(this, 41, 23, textureX, textureY); // Box 161
		bodyModel[31] = new ModelRendererTurbo(this, 57, 25, textureX, textureY); // Box 139
		bodyModel[32] = new ModelRendererTurbo(this, 233, 25, textureX, textureY); // Box 141
		bodyModel[33] = new ModelRendererTurbo(this, 169, 25, textureX, textureY); // Box 156
		bodyModel[34] = new ModelRendererTurbo(this, 425, 25, textureX, textureY); // Box 294
		bodyModel[35] = new ModelRendererTurbo(this, 65, 33, textureX, textureY); // Box 306
		bodyModel[36] = new ModelRendererTurbo(this, 201, 33, textureX, textureY); // Box 0
		bodyModel[37] = new ModelRendererTurbo(this, 441, 25, textureX, textureY); // Box 4
		bodyModel[38] = new ModelRendererTurbo(this, 481, 25, textureX, textureY); // Box 5
		bodyModel[39] = new ModelRendererTurbo(this, 265, 33, textureX, textureY); // Box 6
		bodyModel[40] = new ModelRendererTurbo(this, 217, 1, textureX, textureY); // Box 294
		bodyModel[41] = new ModelRendererTurbo(this, 281, 33, textureX, textureY); // lamp
		bodyModel[42] = new ModelRendererTurbo(this, 305, 33, textureX, textureY); // Box 92
		bodyModel[43] = new ModelRendererTurbo(this, 313, 33, textureX, textureY); // Box 93
		bodyModel[44] = new ModelRendererTurbo(this, 321, 33, textureX, textureY); // Box 94
		bodyModel[45] = new ModelRendererTurbo(this, 328, 33, textureX, textureY); // Box 95
		bodyModel[46] = new ModelRendererTurbo(this, 345, 17, textureX, textureY); // Box 96
		bodyModel[47] = new ModelRendererTurbo(this, 377, 17, textureX, textureY); // Box 97
		bodyModel[48] = new ModelRendererTurbo(this, 337, 33, textureX, textureY); // Box 98
		bodyModel[49] = new ModelRendererTurbo(this, 369, 33, textureX, textureY); // Box 99
		bodyModel[50] = new ModelRendererTurbo(this, 201, 1, textureX, textureY); // Box 100
		bodyModel[51] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // Box 103
		bodyModel[52] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Box 104
		bodyModel[53] = new ModelRendererTurbo(this, 385, 33, textureX, textureY); // Box 105
		bodyModel[54] = new ModelRendererTurbo(this, 409, 33, textureX, textureY); // Box 106
		bodyModel[55] = new ModelRendererTurbo(this, 41, 41, textureX, textureY); // Box 107
		bodyModel[56] = new ModelRendererTurbo(this, 425, 33, textureX, textureY); // Box 109
		bodyModel[57] = new ModelRendererTurbo(this, 114, 41, textureX, textureY); // Box 112
		bodyModel[58] = new ModelRendererTurbo(this, 385, 41, textureX, textureY); // Box 113
		bodyModel[59] = new ModelRendererTurbo(this, 97, 41, textureX, textureY); // Box 116
		bodyModel[60] = new ModelRendererTurbo(this, 457, 49, textureX, textureY); // Box 117
		bodyModel[61] = new ModelRendererTurbo(this, 113, 57, textureX, textureY); // Box 118
		bodyModel[62] = new ModelRendererTurbo(this, 49, 49, textureX, textureY); // Box 120
		bodyModel[63] = new ModelRendererTurbo(this, 57, 49, textureX, textureY); // Box 121
		bodyModel[64] = new ModelRendererTurbo(this, 65, 49, textureX, textureY); // Box 122
		bodyModel[65] = new ModelRendererTurbo(this, 73, 49, textureX, textureY); // Box 123
		bodyModel[66] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 171
		bodyModel[67] = new ModelRendererTurbo(this, 489, 9, textureX, textureY); // Box 173
		bodyModel[68] = new ModelRendererTurbo(this, 41, 25, textureX, textureY); // Box 176
		bodyModel[69] = new ModelRendererTurbo(this, 289, 81, textureX, textureY); // Box 179
		bodyModel[70] = new ModelRendererTurbo(this, 393, 81, textureX, textureY); // Box 180
		bodyModel[71] = new ModelRendererTurbo(this, 433, 81, textureX, textureY); // Box 181
		bodyModel[72] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 182
		bodyModel[73] = new ModelRendererTurbo(this, 241, 65, textureX, textureY); // Box 184
		bodyModel[74] = new ModelRendererTurbo(this, 337, 33, textureX, textureY); // Box 185
		bodyModel[75] = new ModelRendererTurbo(this, 497, 33, textureX, textureY); // Box 186
		bodyModel[76] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 226
		bodyModel[77] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 227
		bodyModel[78] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 228
		bodyModel[79] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 195
		bodyModel[80] = new ModelRendererTurbo(this, 73, 89, textureX, textureY); // Box 196
		bodyModel[81] = new ModelRendererTurbo(this, 113, 89, textureX, textureY); // Box 197
		bodyModel[82] = new ModelRendererTurbo(this, 361, 41, textureX, textureY); // Box 207
		bodyModel[83] = new ModelRendererTurbo(this, 169, 89, textureX, textureY); // Box 208
		bodyModel[84] = new ModelRendererTurbo(this, 337, 57, textureX, textureY); // Box 209
		bodyModel[85] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 8
		bodyModel[86] = new ModelRendererTurbo(this, 25, 97, textureX, textureY); // Box 9
		bodyModel[87] = new ModelRendererTurbo(this, 345, 25, textureX, textureY); // Box 10
		bodyModel[88] = new ModelRendererTurbo(this, 441, 33, textureX, textureY); // Box 11
		bodyModel[89] = new ModelRendererTurbo(this, 481, 33, textureX, textureY); // Box 12
		bodyModel[90] = new ModelRendererTurbo(this, 113, 49, textureX, textureY); // Box 13
		bodyModel[91] = new ModelRendererTurbo(this, 297, 57, textureX, textureY); // Box 15
		bodyModel[92] = new ModelRendererTurbo(this, 417, 41, textureX, textureY); // Box 19
		bodyModel[93] = new ModelRendererTurbo(this, 97, 49, textureX, textureY); // Box 21
		bodyModel[94] = new ModelRendererTurbo(this, 321, 57, textureX, textureY); // Box 24
		bodyModel[95] = new ModelRendererTurbo(this, 441, 57, textureX, textureY); // Box 124
		bodyModel[96] = new ModelRendererTurbo(this, 105, 81, textureX, textureY); // Box 125
		bodyModel[97] = new ModelRendererTurbo(this, 137, 81, textureX, textureY); // Box 127
		bodyModel[98] = new ModelRendererTurbo(this, 313, 65, textureX, textureY); // Box 128
		bodyModel[99] = new ModelRendererTurbo(this, 225, 89, textureX, textureY); // Box 129
		bodyModel[100] = new ModelRendererTurbo(this, 353, 65, textureX, textureY); // Box 130
		bodyModel[101] = new ModelRendererTurbo(this, 17, 97, textureX, textureY); // Box 131
		bodyModel[102] = new ModelRendererTurbo(this, 441, 65, textureX, textureY); // Box 126
		bodyModel[103] = new ModelRendererTurbo(this, 49, 97, textureX, textureY); // Box 449
		bodyModel[104] = new ModelRendererTurbo(this, 209, 97, textureX, textureY); // Box 450
		bodyModel[105] = new ModelRendererTurbo(this, 225, 97, textureX, textureY); // Box 385
		bodyModel[106] = new ModelRendererTurbo(this, 329, 97, textureX, textureY); // Box 386
		bodyModel[107] = new ModelRendererTurbo(this, 361, 97, textureX, textureY); // Box 388
		bodyModel[108] = new ModelRendererTurbo(this, 33, 73, textureX, textureY); // Box 389
		bodyModel[109] = new ModelRendererTurbo(this, 361, 57, textureX, textureY); // Box 390
		bodyModel[110] = new ModelRendererTurbo(this, 345, 97, textureX, textureY); // Box 391
		bodyModel[111] = new ModelRendererTurbo(this, 225, 25, textureX, textureY); // Box 392
		bodyModel[112] = new ModelRendererTurbo(this, 81, 49, textureX, textureY); // Box 393
		bodyModel[113] = new ModelRendererTurbo(this, 121, 49, textureX, textureY); // Box 394
		bodyModel[114] = new ModelRendererTurbo(this, 345, 73, textureX, textureY); // Box 399
		bodyModel[115] = new ModelRendererTurbo(this, 465, 97, textureX, textureY); // Box 400
		bodyModel[116] = new ModelRendererTurbo(this, 241, 65, textureX, textureY); // Box 405
		bodyModel[117] = new ModelRendererTurbo(this, 73, 73, textureX, textureY); // Box 407
		bodyModel[118] = new ModelRendererTurbo(this, 377, 97, textureX, textureY); // Box 408
		bodyModel[119] = new ModelRendererTurbo(this, 1, 73, textureX, textureY); // Box 409
		bodyModel[120] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // Box 414
		bodyModel[121] = new ModelRendererTurbo(this, 89, 105, textureX, textureY); // Box 419
		bodyModel[122] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 420
		bodyModel[123] = new ModelRendererTurbo(this, 137, 73, textureX, textureY); // Box 421
		bodyModel[124] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // Box 422
		bodyModel[125] = new ModelRendererTurbo(this, 145, 105, textureX, textureY); // Box 426
		bodyModel[126] = new ModelRendererTurbo(this, 161, 105, textureX, textureY); // Box 427
		bodyModel[127] = new ModelRendererTurbo(this, 177, 105, textureX, textureY); // Box 431
		bodyModel[128] = new ModelRendererTurbo(this, 73, 81, textureX, textureY); // Box 432
		bodyModel[129] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 434
		bodyModel[130] = new ModelRendererTurbo(this, 257, 81, textureX, textureY); // Box 435
		bodyModel[131] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 436
		bodyModel[132] = new ModelRendererTurbo(this, 297, 81, textureX, textureY); // Box 268
		bodyModel[133] = new ModelRendererTurbo(this, 345, 81, textureX, textureY); // Box 269
		bodyModel[134] = new ModelRendererTurbo(this, 401, 105, textureX, textureY); // Box 64
		bodyModel[135] = new ModelRendererTurbo(this, 369, 81, textureX, textureY); // Box 237
		bodyModel[136] = new ModelRendererTurbo(this, 236, 9, textureX, textureY); // Box 275
		bodyModel[137] = new ModelRendererTurbo(this, 345, 33, textureX, textureY); // Box 279
		bodyModel[138] = new ModelRendererTurbo(this, 121, 41, textureX, textureY); // Box 280
		bodyModel[139] = new ModelRendererTurbo(this, 161, 105, textureX, textureY); // Box 285
		bodyModel[140] = new ModelRendererTurbo(this, 361, 97, textureX, textureY); // Box 286
		bodyModel[141] = new ModelRendererTurbo(this, 1, 73, textureX, textureY); // Box 287
		bodyModel[142] = new ModelRendererTurbo(this, 73, 73, textureX, textureY); // Box 289
		bodyModel[143] = new ModelRendererTurbo(this, 241, 65, textureX, textureY); // Box 290
		bodyModel[144] = new ModelRendererTurbo(this, 345, 73, textureX, textureY); // Box 292
		bodyModel[145] = new ModelRendererTurbo(this, 329, 97, textureX, textureY); // Box 293
		bodyModel[146] = new ModelRendererTurbo(this, 377, 97, textureX, textureY); // Box 294
		bodyModel[147] = new ModelRendererTurbo(this, 465, 97, textureX, textureY); // Box 295
		bodyModel[148] = new ModelRendererTurbo(this, 145, 105, textureX, textureY); // Box 296
		bodyModel[149] = new ModelRendererTurbo(this, 33, 73, textureX, textureY); // Box 297
		bodyModel[150] = new ModelRendererTurbo(this, 121, 49, textureX, textureY); // Box 298
		bodyModel[151] = new ModelRendererTurbo(this, 361, 57, textureX, textureY); // Box 299
		bodyModel[152] = new ModelRendererTurbo(this, 345, 97, textureX, textureY); // Box 300
		bodyModel[153] = new ModelRendererTurbo(this, 81, 49, textureX, textureY); // Box 301
		bodyModel[154] = new ModelRendererTurbo(this, 225, 25, textureX, textureY); // Box 302
		bodyModel[155] = new ModelRendererTurbo(this, 225, 97, textureX, textureY); // Box 304
		bodyModel[156] = new ModelRendererTurbo(this, 89, 105, textureX, textureY); // Box 305
		bodyModel[157] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // Box 306
		bodyModel[158] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 308
		bodyModel[159] = new ModelRendererTurbo(this, 73, 81, textureX, textureY); // Box 309
		bodyModel[160] = new ModelRendererTurbo(this, 257, 81, textureX, textureY); // Box 310
		bodyModel[161] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 313
		bodyModel[162] = new ModelRendererTurbo(this, 177, 105, textureX, textureY); // Box 314
		bodyModel[163] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // Box 315
		bodyModel[164] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 316
		bodyModel[165] = new ModelRendererTurbo(this, 137, 73, textureX, textureY); // Box 317
		bodyModel[166] = new ModelRendererTurbo(this, 73, 89, textureX, textureY); // Box 318
		bodyModel[167] = new ModelRendererTurbo(this, 361, 89, textureX, textureY); // Box 319
		bodyModel[168] = new ModelRendererTurbo(this, 113, 89, textureX, textureY); // Box 320
		bodyModel[169] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 322
		bodyModel[170] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 323
		bodyModel[171] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 324
		bodyModel[172] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 325
		bodyModel[173] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 326
		bodyModel[174] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 327
		bodyModel[175] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 328
		bodyModel[176] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 329
		bodyModel[177] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 330
		bodyModel[178] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 331
		bodyModel[179] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 332
		bodyModel[180] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 333
		bodyModel[181] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 334
		bodyModel[182] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 335
		bodyModel[183] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 336
		bodyModel[184] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 337
		bodyModel[185] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 338
		bodyModel[186] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 339
		bodyModel[187] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 340
		bodyModel[188] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 341
		bodyModel[189] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 342
		bodyModel[190] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 343
		bodyModel[191] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 344
		bodyModel[192] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 345
		bodyModel[193] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 346
		bodyModel[194] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 347
		bodyModel[195] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 348
		bodyModel[196] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 349
		bodyModel[197] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 376
		bodyModel[198] = new ModelRendererTurbo(this, 313, 57, textureX, textureY); // Box 379
		bodyModel[199] = new ModelRendererTurbo(this, 375, 96, textureX, textureY); // Box 380
		bodyModel[200] = new ModelRendererTurbo(this, 476, 100, textureX, textureY); // Box 325
		bodyModel[201] = new ModelRendererTurbo(this, 495, 101, textureX, textureY); // Box 326
		bodyModel[202] = new ModelRendererTurbo(this, 223, 92, textureX, textureY,"lamp"); // lamp
		bodyModel[203] = new ModelRendererTurbo(this, 495, 115, textureX, textureY); // Box 333
		bodyModel[204] = new ModelRendererTurbo(this, 457, 62, textureX, textureY); // Box 333
		bodyModel[205] = new ModelRendererTurbo(this, 169, 38, textureX, textureY); // Box 336
		bodyModel[206] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // lamp
		bodyModel[207] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // lamp
		bodyModel[208] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // lamp
		bodyModel[209] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // lamp
		bodyModel[210] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // lamp
		bodyModel[211] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // lamp
		bodyModel[212] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // lamp
		bodyModel[213] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // lamp
		bodyModel[214] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // lamp
		bodyModel[215] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // lamp
		bodyModel[216] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // lamp
		bodyModel[217] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // lamp
		bodyModel[218] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // lamp
		bodyModel[219] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // lamp
		bodyModel[220] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // lamp
		bodyModel[221] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // lamp
		bodyModel[222] = new ModelRendererTurbo(this, 198, 65, textureX, textureY); // Box 361
		bodyModel[223] = new ModelRendererTurbo(this, 198, 76, textureX, textureY); // Box 362
		bodyModel[224] = new ModelRendererTurbo(this, 198, 52, textureX, textureY); // Box 363
		bodyModel[225] = new ModelRendererTurbo(this, 170, 76, textureX, textureY); // Box 364
		bodyModel[226] = new ModelRendererTurbo(this, 170, 65, textureX, textureY); // Box 365
		bodyModel[227] = new ModelRendererTurbo(this, 170, 52, textureX, textureY); // Box 366
		bodyModel[228] = new ModelRendererTurbo(this, 6, 54, textureX, textureY); // Box 367
		bodyModel[229] = new ModelRendererTurbo(this, 226, 67, textureX, textureY); // Box 368
		bodyModel[230] = new ModelRendererTurbo(this, 226, 78, textureX, textureY); // Box 369
		bodyModel[231] = new ModelRendererTurbo(this, 226, 54, textureX, textureY); // Box 370
		bodyModel[232] = new ModelRendererTurbo(this, 226, 51, textureX, textureY); // Box 371
		bodyModel[233] = new ModelRendererTurbo(this, 259, 51, textureX, textureY); // Box 372
		bodyModel[234] = new ModelRendererTurbo(this, 259, 54, textureX, textureY); // Box 373
		bodyModel[235] = new ModelRendererTurbo(this, 259, 67, textureX, textureY); // Box 374
		bodyModel[236] = new ModelRendererTurbo(this, 259, 78, textureX, textureY); // Box 375
		bodyModel[237] = new ModelRendererTurbo(this, 153, 46, textureX, textureY); // Box 376
		bodyModel[238] = new ModelRendererTurbo(this, 109, 60, textureX, textureY); // Box 377
		bodyModel[239] = new ModelRendererTurbo(this, 153, 41, textureX, textureY); // Box 378
		bodyModel[240] = new ModelRendererTurbo(this, 1, 140, textureX, textureY); // Box 13
		bodyModel[241] = new ModelRendererTurbo(this, 17, 140, textureX, textureY); // Box 16
		bodyModel[242] = new ModelRendererTurbo(this, 33, 140, textureX, textureY); // Box 17
		bodyModel[243] = new ModelRendererTurbo(this, 49, 140, textureX, textureY); // Box 18
		bodyModel[244] = new ModelRendererTurbo(this, 65, 140, textureX, textureY); // Box 19
		bodyModel[245] = new ModelRendererTurbo(this, 81, 140, textureX, textureY); // Box 20
		bodyModel[246] = new ModelRendererTurbo(this, 93, 140, textureX, textureY); // Box 21
		bodyModel[247] = new ModelRendererTurbo(this, 121, 140, textureX, textureY); // Box 22
		bodyModel[248] = new ModelRendererTurbo(this, 145, 140, textureX, textureY); // Box 24
		bodyModel[249] = new ModelRendererTurbo(this, 169, 140, textureX, textureY); // Box 25
		bodyModel[250] = new ModelRendererTurbo(this, 193, 140, textureX, textureY); // Box 26
		bodyModel[251] = new ModelRendererTurbo(this, 97, 140, textureX, textureY); // Box 27
		bodyModel[252] = new ModelRendererTurbo(this, 137, 140, textureX, textureY); // Box 29
		bodyModel[253] = new ModelRendererTurbo(this, 217, 140, textureX, textureY); // Box 30
		bodyModel[254] = new ModelRendererTurbo(this, 233, 140, textureX, textureY); // Box 31
		bodyModel[255] = new ModelRendererTurbo(this, 131, 151, textureX, textureY); // Box 40
		bodyModel[256] = new ModelRendererTurbo(this, 140, 151, textureX, textureY); // Box 41
		bodyModel[257] = new ModelRendererTurbo(this, 149, 151, textureX, textureY); // Box 42
		bodyModel[258] = new ModelRendererTurbo(this, 158, 151, textureX, textureY); // Box 43
		bodyModel[259] = new ModelRendererTurbo(this, 414, 65, textureX, textureY); // Box 61
		bodyModel[260] = new ModelRendererTurbo(this, 389, 65, textureX, textureY); // Box 88
		bodyModel[261] = new ModelRendererTurbo(this, 389, 58, textureX, textureY); // Box 172
		bodyModel[262] = new ModelRendererTurbo(this, 396, 58, textureX, textureY); // Box 176
		bodyModel[263] = new ModelRendererTurbo(this, 405, 58, textureX, textureY); // Box 404
		bodyModel[264] = new ModelRendererTurbo(this, 414, 58, textureX, textureY); // Box 405
		bodyModel[265] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 406
		bodyModel[266] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 407
		bodyModel[267] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 408
		bodyModel[268] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 409
		bodyModel[269] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 410
		bodyModel[270] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 411
		bodyModel[271] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 412
		bodyModel[272] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 413
		bodyModel[273] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 414
		bodyModel[274] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 415
		bodyModel[275] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 416
		bodyModel[276] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 417
		bodyModel[277] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 418
		bodyModel[278] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 419
		bodyModel[279] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 420
		bodyModel[280] = new ModelRendererTurbo(this, 283, 121, textureX, textureY); // Box 367
		bodyModel[281] = new ModelRendererTurbo(this, 286, 121, textureX, textureY); // Box 368
		bodyModel[282] = new ModelRendererTurbo(this, 291, 121, textureX, textureY); // Box 369
		bodyModel[283] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 376
		bodyModel[284] = new ModelRendererTurbo(this, 330, 124, textureX, textureY); // Box 377
		bodyModel[285] = new ModelRendererTurbo(this, 357, 124, textureX, textureY); // Box 378
		bodyModel[286] = new ModelRendererTurbo(this, 318, 124, textureX, textureY); // Box 379
		bodyModel[287] = new ModelRendererTurbo(this, 1, 188, textureX, textureY); // Box 377
		bodyModel[288] = new ModelRendererTurbo(this, 1, 188, textureX, textureY); // Box 378
		bodyModel[289] = new ModelRendererTurbo(this, 1, 177, textureX, textureY); // Box 379
		bodyModel[290] = new ModelRendererTurbo(this, 19, 198, textureX, textureY); // Box 380
		bodyModel[291] = new ModelRendererTurbo(this, 2, 203, textureX, textureY); // Box 381
		bodyModel[292] = new ModelRendererTurbo(this, 2, 196, textureX, textureY); // Box 382
		bodyModel[293] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 313
		bodyModel[294] = new ModelRendererTurbo(this, 330, 124, textureX, textureY); // Box 314
		bodyModel[295] = new ModelRendererTurbo(this, 302, 121, textureX, textureY); // Box 315
		bodyModel[296] = new ModelRendererTurbo(this, 309, 121, textureX, textureY); // Box 316
		bodyModel[297] = new ModelRendererTurbo(this, 417, 9, textureX, textureY); // Box 317
		bodyModel[298] = new ModelRendererTurbo(this, 241, 9, textureX, textureY); // Box 319
		bodyModel[299] = new ModelRendererTurbo(this, 481, 33, textureX, textureY); // Box 110
		bodyModel[300] = new ModelRendererTurbo(this, 81, 41, textureX, textureY); // Box 111
		bodyModel[301] = new ModelRendererTurbo(this, 110, 188, textureX, textureY); // Box 318
		bodyModel[302] = new ModelRendererTurbo(this, 110, 177, textureX, textureY); // Box 319
		bodyModel[303] = new ModelRendererTurbo(this, 110, 188, textureX, textureY); // Box 320
		bodyModel[304] = new ModelRendererTurbo(this, 1, 19, textureX, textureY); // Box 321
		bodyModel[305] = new ModelRendererTurbo(this, 1, 19, textureX, textureY); // Box 322
		bodyModel[306] = new ModelRendererTurbo(this, 3, 52, textureX, textureY); // Box 336
		bodyModel[307] = new ModelRendererTurbo(this, 6, 60, textureX, textureY); // Box 337
		bodyModel[308] = new ModelRendererTurbo(this, 6, 60, textureX, textureY); // Box 325
		bodyModel[309] = new ModelRendererTurbo(this, 3, 52, textureX, textureY); // Box 326
		bodyModel[310] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 30
		bodyModel[311] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 31
		bodyModel[312] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 32
		bodyModel[313] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 33
		bodyModel[314] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 331
		bodyModel[315] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 332
		bodyModel[316] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 336
		bodyModel[317] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 337
		bodyModel[318] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 338
		bodyModel[319] = new ModelRendererTurbo(this, 11, 225, textureX, textureY); // Box 340
		bodyModel[320] = new ModelRendererTurbo(this, 18, 225, textureX, textureY); // Box 341
		bodyModel[321] = new ModelRendererTurbo(this, 11, 225, textureX, textureY); // Box 342
		bodyModel[322] = new ModelRendererTurbo(this, 18, 225, textureX, textureY); // Box 343
		bodyModel[323] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 344
		bodyModel[324] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 345
		bodyModel[325] = new ModelRendererTurbo(this, 3, 225, textureX, textureY); // Box 346
		bodyModel[326] = new ModelRendererTurbo(this, 1, 231, textureX, textureY); // Box 8
		bodyModel[327] = new ModelRendererTurbo(this, 25, 231, textureX, textureY); // Box 9
		bodyModel[328] = new ModelRendererTurbo(this, 89, 217, textureX, textureY); // Box 10
		bodyModel[329] = new ModelRendererTurbo(this, 99, 239, textureX, textureY); // Box 11
		bodyModel[330] = new ModelRendererTurbo(this, 99, 223, textureX, textureY); // Box 12
		bodyModel[331] = new ModelRendererTurbo(this, 79, 228, textureX, textureY); // Box 13
		bodyModel[332] = new ModelRendererTurbo(this, 104, 223, textureX, textureY); // Box 15
		bodyModel[333] = new ModelRendererTurbo(this, 83, 239, textureX, textureY); // Box 19
		bodyModel[334] = new ModelRendererTurbo(this, 97, 231, textureX, textureY); // Box 21
		bodyModel[335] = new ModelRendererTurbo(this, 87, 228, textureX, textureY); // Box 24
		bodyModel[336] = new ModelRendererTurbo(this, 49, 231, textureX, textureY); // Box 449
		bodyModel[337] = new ModelRendererTurbo(this, 71, 233, textureX, textureY); // Box 58
		bodyModel[338] = new ModelRendererTurbo(this, 24, 210, textureX, textureY); // Box 59
		bodyModel[339] = new ModelRendererTurbo(this, 90, 204, textureX, textureY); // Box 360
		bodyModel[340] = new ModelRendererTurbo(this, 124, 188, textureX, textureY); // Box 356
		bodyModel[341] = new ModelRendererTurbo(this, 154, 194, textureX, textureY); // Box 357
		bodyModel[342] = new ModelRendererTurbo(this, 145, 194, textureX, textureY); // Box 358
		bodyModel[343] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Box 401
		bodyModel[344] = new ModelRendererTurbo(this, 481, 97, textureX, textureY); // Box 410
		bodyModel[345] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Box 291
		bodyModel[346] = new ModelRendererTurbo(this, 481, 97, textureX, textureY); // Box 311
		bodyModel[347] = new ModelRendererTurbo(this, 91, 107, textureX, textureY); // Box 24
		bodyModel[348] = new ModelRendererTurbo(this, 179, 107, textureX, textureY); // Box 458
		bodyModel[349] = new ModelRendererTurbo(this, 179, 107, textureX, textureY); // Box 284
		bodyModel[350] = new ModelRendererTurbo(this, 91, 107, textureX, textureY); // Box 303
		bodyModel[351] = new ModelRendererTurbo(this, 421, 59, textureX, textureY); // Box 406
		bodyModel[352] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 433
		bodyModel[353] = new ModelRendererTurbo(this, 421, 59, textureX, textureY); // Box 288
		bodyModel[354] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 307
		bodyModel[355] = new ModelRendererTurbo(this, 275, 125, textureX, textureY); // Box 615
		bodyModel[356] = new ModelRendererTurbo(this, 288, 125, textureX, textureY); // Box 617
		bodyModel[357] = new ModelRendererTurbo(this, 267, 128, textureX, textureY); // Box 618
		bodyModel[358] = new ModelRendererTurbo(this, 270, 131, textureX, textureY); // Box 414
		bodyModel[359] = new ModelRendererTurbo(this, 253, 124, textureX, textureY); // Box 417
		bodyModel[360] = new ModelRendererTurbo(this, 262, 135, textureX, textureY); // Box 411
		bodyModel[361] = new ModelRendererTurbo(this, 270, 142, textureX, textureY); // Box 366
		bodyModel[362] = new ModelRendererTurbo(this, 262, 142, textureX, textureY); // Box 367
		bodyModel[363] = new ModelRendererTurbo(this, 275, 137, textureX, textureY); // Box 368
		bodyModel[364] = new ModelRendererTurbo(this, 288, 136, textureX, textureY); // Box 369
		bodyModel[365] = new ModelRendererTurbo(this, 267, 147, textureX, textureY); // Box 370
		bodyModel[366] = new ModelRendererTurbo(this, 197, 242, textureX, textureY); // Box 366
		bodyModel[367] = new ModelRendererTurbo(this, 185, 246, textureX, textureY); // Box 367
		bodyModel[368] = new ModelRendererTurbo(this, 185, 246, textureX, textureY); // Box 368
		bodyModel[369] = new ModelRendererTurbo(this, 165, 232, textureX, textureY); // Box 369
		bodyModel[370] = new ModelRendererTurbo(this, 129, 237, textureX, textureY); // Box 370
		bodyModel[371] = new ModelRendererTurbo(this, 188, 243, textureX, textureY); // Box 371
		bodyModel[372] = new ModelRendererTurbo(this, 200, 248, textureX, textureY); // Box 372
		bodyModel[373] = new ModelRendererTurbo(this, 200, 248, textureX, textureY); // Box 373
		bodyModel[374] = new ModelRendererTurbo(this, 181, 230, textureX, textureY); // Box 374
		bodyModel[375] = new ModelRendererTurbo(this, 200, 248, textureX, textureY); // Box 375
		bodyModel[376] = new ModelRendererTurbo(this, 188, 243, textureX, textureY); // Box 376
		bodyModel[377] = new ModelRendererTurbo(this, 200, 248, textureX, textureY); // Box 377
		bodyModel[378] = new ModelRendererTurbo(this, 181, 230, textureX, textureY); // Box 378

		bodyModel[0].addShapeBox(0F, 0F, 0F, 4, 1, 16, 0F,2F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 1F, 2F, 0F, 0F, 2F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 1F, 2F, 0F, 0F); // Box 9
		bodyModel[0].setRotationPoint(-20.5F, 4F, -8F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 43, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 62
		bodyModel[1].setRotationPoint(-16.5F, -19F, -8F);

		bodyModel[2].addShapeBox(0F, 0F, 0F, 5, 2, 19, 0F,0F, 0F, -2.3F, 0F, 0F, -0.25F, 0F, 0F, -1F, 0F, 0F, -3.3F, 0F, 0F, -1.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -2.5F); // Box 63
		bodyModel[2].setRotationPoint(-26.5F, -18F, -9F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 2, 2, 15, 0F,0F, 0F, -1.1F, 0F, 0.5F, -0.5F, 0F, 0.5F, -1.5F, 0F, 0F, -2.1F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -1.5F); // Box 65
		bodyModel[3].setRotationPoint(-28.5F, -18F, -7F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 1, 2, 13, 0F,0F, -0.5F, -1F, 0F, 0F, -0.1F, 0F, 0F, -1.1F, 0F, -0.5F, -2F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -1.5F); // Box 66
		bodyModel[4].setRotationPoint(-29.5F, -18F, -6F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 1, 1, 11, 0F,-0.35F, -0.7F, -1F, 0F, 0.5F, 0F, 0F, 0.5F, -1F, -0.35F, -0.7F, -2F, -0.3F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, -0.3F, 0F, -2F); // Box 67
		bodyModel[5].setRotationPoint(-30.5F, -17F, -5F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, -0.375F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F); // Box 72
		bodyModel[6].setRotationPoint(-17.5F, -21F, 6.25F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 39, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 75
		bodyModel[7].setRotationPoint(-15.5F, -21F, -8.5F);

		bodyModel[8].addShapeBox(0F, 0F, 0F, 39, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 76
		bodyModel[8].setRotationPoint(-15.5F, -21F, 7.5F);

		bodyModel[9].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,-0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.375F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 77
		bodyModel[9].setRotationPoint(-17.5F, -21F, -8.25F);

		bodyModel[10].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 76
		bodyModel[10].setRotationPoint(4.5F, -14F, 9.5F);

		bodyModel[11].addShapeBox(-2F, 0F, -0.5F, 2, 20, 1, 0F,0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F); // Box 79
		bodyModel[11].setRotationPoint(14.5F, -14F, 9.5F);

		bodyModel[12].addShapeBox(-2F, 0F, -0.5F, 2, 20, 1, 0F,0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F); // Box 80
		bodyModel[12].setRotationPoint(12F, -14F, 9.5F);

		bodyModel[13].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 81
		bodyModel[13].setRotationPoint(7F, -14F, 9.5F);

		bodyModel[14].addShapeBox(0F, 0F, 0F, 43, 2, 2, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 140
		bodyModel[14].setRotationPoint(-16.5F, -18F, 8F);

		bodyModel[15].addShapeBox(0F, 0F, 0F, 43, 2, 2, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F); // Box 142
		bodyModel[15].setRotationPoint(-16.5F, -18F, -10F);

		bodyModel[16].addShapeBox(0F, 0F, 0F, 5, 1, 17, 0F,0F, -0.5F, -1.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, -0.5F, -2.5F, 0F, 0F, -1.3F, 0F, 0F, 0.75F, 0F, 0F, 0F, 0F, 0F, -2.3F); // Box 145
		bodyModel[16].setRotationPoint(-26.5F, -19F, -8F);

		bodyModel[17].addShapeBox(0F, 0F, 0F, 5, 2, 19, 0F,0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, -0.5F); // Box 154
		bodyModel[17].setRotationPoint(-21.5F, -18F, -9F);

		bodyModel[18].addShapeBox(0F, 0F, 0F, 5, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0.75F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 165
		bodyModel[18].setRotationPoint(-21.5F, -19F, -8F);

		bodyModel[19].addShapeBox(0F, 0F, 0F, 2, 20, 1, 0F,0F, 1F, 0.5F, 0F, 1F, -0.5F, 0F, 1F, 0.5F, 0F, 1F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F); // Box 184
		bodyModel[19].setRotationPoint(-28.5F, -15F, 6F);

		bodyModel[20].addShapeBox(0F, 0F, 0F, 1, 20, 2, 0F,0F, 1F, 0F, 0F, 1F, -1F, 0F, 1F, 0F, 0F, 1F, -1F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -1F); // Box 185
		bodyModel[20].setRotationPoint(-29.5F, -15F, 4.5F);

		bodyModel[21].addShapeBox(0F, 0F, 0F, 1, 1, 13, 0F,-0.5F, 0F, -2F, 0F, 0F, -1F, 0F, 0F, -2F, -0.5F, 0F, -3F, -0.5F, 0F, -2F, 0F, 0F, -1F, 0F, 0F, -2F, -0.5F, 0F, -3F); // Box 186
		bodyModel[21].setRotationPoint(-31.5F, 4F, -6F);

		bodyModel[22].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, 0.25F, 0.5F, 1F, -0.75F, 0.5F, 1F, 0.25F, 0F, 1F, -0.75F, 0F, 0F, 0.25F, 0.5F, 0F, -0.75F, 0.5F, 0F, 0.25F, 0F, 0F, -0.75F); // Box 82
		bodyModel[22].setRotationPoint(-26.5F, -14F, 7.5F);

		bodyModel[23].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -1F); // Box 58
		bodyModel[23].setRotationPoint(-26.5F, -16F, 5.5F);

		bodyModel[24].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, 0F, 0.5F, 1F, -1F, 0.5F, 1F, 0.5F, 0F, 1F, -0.5F, 0F, 0F, 0F, 0.5F, 0F, -1F, 0.5F, 0F, 0.5F, 0F, 0F, -0.5F); // Box 86
		bodyModel[24].setRotationPoint(-24F, -14F, 8.25F);

		bodyModel[25].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0F); // Box 179
		bodyModel[25].setRotationPoint(-21.5F, -16F, 6.5F);

		bodyModel[26].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.5F, 0.5F, 1F, -0.75F, 0.5F, 1F, 0.25F, 0F, 1F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.75F, 0.5F, 0F, 0.25F, 0F, 0F, 0F); // Box 180
		bodyModel[26].setRotationPoint(-21.5F, -14F, 8.75F);

		bodyModel[27].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.5F, 0.5F, 1F, -0.75F, 0.5F, 1F, 0.25F, 0F, 1F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.75F, 0.5F, 0F, 0.25F, 0F, 0F, 0F); // Box 181
		bodyModel[27].setRotationPoint(-19F, -14F, 9F);

		bodyModel[28].addShapeBox(0F, 1F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0F); // Box 182
		bodyModel[28].setRotationPoint(-21.5F, 5F, 6.5F);

		bodyModel[29].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -1F); // Box 183
		bodyModel[29].setRotationPoint(-26.5F, 6F, 5.5F);

		bodyModel[30].addShapeBox(0F, 0F, 0F, 1, 3, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 161
		bodyModel[30].setRotationPoint(-29.5F, -16F, -5F);

		bodyModel[31].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 139
		bodyModel[31].setRotationPoint(-16.5F, -16F, 9F);

		bodyModel[32].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 141
		bodyModel[32].setRotationPoint(-16.5F, -16F, -10F);

		bodyModel[33].addShapeBox(0F, 0F, 0F, 21, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 156
		bodyModel[33].setRotationPoint(-16.5F, -14.5F, 9F);

		bodyModel[34].addShapeBox(0F, 0F, 0F, 10, 3, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 294
		bodyModel[34].setRotationPoint(4.5F, -18F, 6F);

		bodyModel[35].addShapeBox(0F, 0F, 0F, 37, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F); // Box 306
		bodyModel[35].setRotationPoint(-13.5F, -18F, -1F);

		bodyModel[36].addShapeBox(0F, 0F, 0F, 21, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 0
		bodyModel[36].setRotationPoint(-16.5F, 4F, -9F);

		bodyModel[37].addShapeBox(0F, 0F, 0F, 10, 3, 13, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 4
		bodyModel[37].setRotationPoint(4.5F, 4F, -6F);

		bodyModel[38].addBox(0F, 0F, 0F, 10, 1, 3, 0F); // Box 5
		bodyModel[38].setRotationPoint(4.5F, 6F, 7.1F);

		bodyModel[39].addBox(0F, 0F, 0F, 10, 1, 1, 0F); // Box 6
		bodyModel[39].setRotationPoint(4.5F, 4F, 7F);

		bodyModel[40].addShapeBox(0F, 0F, 0F, 1, 3, 3, 0F,0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 2F, -1F, 0F, 2F, -1F, 0F, 0F, 0F, 0F); // Box 294
		bodyModel[40].setRotationPoint(-30F, -19.5F, -1.5F);

		bodyModel[41].addShapeBox(0F, 0F, 0F, 1, 9, 9, 0F,0F, 0F, -6F, 0F, -0.3F, -6F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, -6F, -6F, 0F, -6F, -6F, 0F, -6F, 0F, 0F, -6F, 0F); // lamp
		bodyModel[41].setRotationPoint(-30.01F, -19.5F, -7.5F);

		bodyModel[42].addShapeBox(0F, 0F, 0F, 2, 20, 1, 0F,0F, 1F, -0.5F, 0F, 1F, 0.5F, 0F, 1F, -0.5F, 0F, 1F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F); // Box 92
		bodyModel[42].setRotationPoint(-28.5F, -15F, -7F);

		bodyModel[43].addShapeBox(0F, 0F, 0F, 1, 20, 2, 0F,0F, 1F, -1F, 0F, 1F, 0F, 0F, 1F, -1F, 0F, 1F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F); // Box 93
		bodyModel[43].setRotationPoint(-29.5F, -15F, -6.5F);

		bodyModel[44].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, 0F, 0.5F, 1F, 0.25F, 0.5F, 1F, -0.75F, 0F, 1F, -0.5F, 0F, 0F, 0F, 0.5F, 0F, 0.25F, 0.5F, 0F, -0.75F, 0F, 0F, -0.5F); // Box 94
		bodyModel[44].setRotationPoint(-19F, -14F, -9F);

		bodyModel[45].addShapeBox(0F, 0F, -0.5F, 3, 20, 1, 0F,0F, 1F, -0.2F, 0.5F, 1F, 0.25F, 0.5F, 1F, -0.75F, 0F, 1F, -0.2F, 0F, 0F, -0.2F, 0.5F, 0F, 0.25F, 0.5F, 0F, -0.75F, 0F, 0F, -0.2F); // Box 95
		bodyModel[45].setRotationPoint(-22.5F, -14F, -8.75F);

		bodyModel[46].addShapeBox(0F, 1F, 0F, 6, 1, 3, 0F,0F, 0F, -0.4F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.4F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F); // Box 96
		bodyModel[46].setRotationPoint(-22.5F, 5F, -9.5F);

		bodyModel[47].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F); // Box 97
		bodyModel[47].setRotationPoint(-21.5F, -16F, -9.5F);

		bodyModel[48].addBox(0F, 0F, 0F, 1, 2, 14, 0F); // Box 98
		bodyModel[48].setRotationPoint(-23.5F, 5F, -8F);

		bodyModel[49].addShapeBox(0F, 0F, 0F, 4, 20, 1, 0F,0F, 1F, -1.5F, 0F, 1F, 0.1F, 0F, 1F, -0.1F, 0F, 1F, 1.5F, 0F, 0F, -1.5F, 0F, 0F, 0.1F, 0F, 0F, -0.1F, 0F, 0F, 1.5F); // Box 99
		bodyModel[49].setRotationPoint(-26.5F, -15F, -9F);

		bodyModel[50].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, -0.2F, 0.3F, 0F, -0.2F, -0.7F); // Box 100
		bodyModel[50].setRotationPoint(-28.5F, 5F, 6F);

		bodyModel[51].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, -0.2F, -0.7F, 0F, -0.2F, 0.3F, 0F, 0F, -0.5F, 0F, 0F, 0.5F); // Box 103
		bodyModel[51].setRotationPoint(-28.5F, 5F, -7F);

		bodyModel[52].addShapeBox(0F, 0F, 0F, 4, 2, 1, 0F,0F, 0F, -1.5F, 0F, 0F, 0.1F, 0F, 0F, -0.1F, 0F, 0F, 1.5F, 0F, -0.2F, -1.7F, 0F, -0.2F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 1.5F); // Box 104
		bodyModel[52].setRotationPoint(-26.5F, 5F, -9F);

		bodyModel[53].addBox(0F, 0F, 0F, 10, 1, 1, 0F); // Box 105
		bodyModel[53].setRotationPoint(4.5F, 4F, -8F);

		bodyModel[54].addBox(0F, 0F, 0F, 10, 1, 3, 0F); // Box 106
		bodyModel[54].setRotationPoint(4.5F, 6F, -10.1F);

		bodyModel[55].addShapeBox(0F, 0F, 0F, 21, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 107
		bodyModel[55].setRotationPoint(-16.5F, 5F, 9.1F);

		bodyModel[56].addShapeBox(0F, 0F, 0F, 2, 9, 11, 0F,-0.8F, 0.5F, -1.5F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.8F, 0.5F, -1.5F, -0.8F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, -1.5F); // Box 109
		bodyModel[56].setRotationPoint(-31.5F, -4F, -5.5F);

		bodyModel[57].addShapeBox(0F, 0F, 0F, 4, 1, 13, 0F,0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 2F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 2F, 0F, 0F, 0F); // Box 112
		bodyModel[57].setRotationPoint(-26.5F, 4F, -7F);

		bodyModel[58].addShapeBox(0F, 0F, 0F, 3, 2, 14, 0F,3F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 3F, 0F, -1F, 3F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 3F, 0F, -1F); // Box 113
		bodyModel[58].setRotationPoint(-19.5F, 5F, -6.5F);

		bodyModel[59].addShapeBox(0F, 0F, 0F, 10, 3, 3, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F); // Box 116
		bodyModel[59].setRotationPoint(4.5F, -18F, -9F);

		bodyModel[60].addShapeBox(0F, 0F, 0F, 21, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.2F, 0F, 0.5F, 0.2F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 117
		bodyModel[60].setRotationPoint(-16.5F, -15F, -10F);

		bodyModel[61].addShapeBox(0F, 0F, 0F, 21, 2, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 118
		bodyModel[61].setRotationPoint(-16.5F, 5F, -10.1F);

		bodyModel[62].addShapeBox(-2F, 0F, -0.5F, 2, 20, 1, 0F,0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F); // Box 120
		bodyModel[62].setRotationPoint(14.5F, -14F, -9.5F);

		bodyModel[63].addShapeBox(-2F, 0F, -0.5F, 2, 20, 1, 0F,0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F); // Box 121
		bodyModel[63].setRotationPoint(12F, -14F, -9.5F);

		bodyModel[64].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 122
		bodyModel[64].setRotationPoint(4.5F, -14F, -9.5F);

		bodyModel[65].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 123
		bodyModel[65].setRotationPoint(7F, -14F, -9.5F);

		bodyModel[66].addShapeBox(0F, 0F, 0F, 3, 1, 10, 0F,0F, 0F, -0.5F, 0F, 0F, 1F, 0F, 0F, 2F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 1F, 0F, 0F, 2F, 0F, 0F, 0.5F); // Box 171
		bodyModel[66].setRotationPoint(-29.5F, 4F, -5.5F);

		bodyModel[67].addShapeBox(0F, 0F, 0F, 4, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -0.5F); // Box 173
		bodyModel[67].setRotationPoint(-26.5F, 5F, 5F);

		bodyModel[68].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.3F, 0F, 0F, -0.2F); // Box 176
		bodyModel[68].setRotationPoint(-27.5F, 4.9F, 5F);

		bodyModel[69].addShapeBox(0F, 0F, 0F, 1, 2, 17, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 179
		bodyModel[69].setRotationPoint(14.5F, 5F, -8.5F);

		bodyModel[70].addShapeBox(0F, 0F, 0F, 1, 2, 17, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 180
		bodyModel[70].setRotationPoint(3.5F, 5F, -8.5F);

		bodyModel[71].addShapeBox(0F, 0F, 0F, 1, 2, 17, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 181
		bodyModel[71].setRotationPoint(-16.5F, 5F, -8F);

		bodyModel[72].addShapeBox(0F, 0F, 0F, 7, 7, 5, 0F,0F, 0F, -4F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -4F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 182
		bodyModel[72].setRotationPoint(-29.75F, -3F, -8.5F);

		bodyModel[73].addBox(0F, 0F, 0F, 2, 2, 6, 0F); // Box 184
		bodyModel[73].setRotationPoint(-29.5F, -3.5F, -3.5F);

		bodyModel[74].addShapeBox(0F, 0F, 0F, 1, 8, 3, 0F,-1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F); // Box 185
		bodyModel[74].setRotationPoint(-27.5F, -3.5F, 3F);

		bodyModel[75].addShapeBox(0F, 0F, 0F, 2, 7, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 186
		bodyModel[75].setRotationPoint(-29.5F, -3F, 2.5F);

		bodyModel[76].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 226
		bodyModel[76].setRotationPoint(2.5F, -5F, -8.5F);

		bodyModel[77].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 227
		bodyModel[77].setRotationPoint(-0.5F, 0F, -8.5F);

		bodyModel[78].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 228
		bodyModel[78].setRotationPoint(1.75F, 0.5F, -6F);

		bodyModel[79].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 195
		bodyModel[79].setRotationPoint(3.25F, -18F, -5.5F);

		bodyModel[80].addBox(0F, 0F, 0F, 0, 22, 4, 0F); // Box 196
		bodyModel[80].setRotationPoint(14.6F, -17F, -8.5F);

		bodyModel[81].addBox(0F, 0F, 0F, 0, 22, 4, 0F); // Box 197
		bodyModel[81].setRotationPoint(14.5F, -17F, 4.5F);

		bodyModel[82].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 207
		bodyModel[82].setRotationPoint(-25.25F, 0.5F, -1F);

		bodyModel[83].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 208
		bodyModel[83].setRotationPoint(-26.5F, 0F, -3F);

		bodyModel[84].addShapeBox(0F, 0F, 0F, 1, 4, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 209
		bodyModel[84].setRotationPoint(-23.5F, -4F, -3F);

		bodyModel[85].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 8
		bodyModel[85].setRotationPoint(13F, -33.75F, -4.75F);

		bodyModel[86].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 9
		bodyModel[86].setRotationPoint(15F, -33.75F, -4.75F);

		bodyModel[87].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 10
		bodyModel[87].setRotationPoint(13F, -32.75F, -5F);

		bodyModel[88].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 11
		bodyModel[88].setRotationPoint(13F, -32.75F, 4F);

		bodyModel[89].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 12
		bodyModel[89].setRotationPoint(15F, -33.75F, -6.75F);

		bodyModel[90].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 13
		bodyModel[90].setRotationPoint(13F, -33.75F, 5.25F);

		bodyModel[91].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 15
		bodyModel[91].setRotationPoint(13F, -33.75F, -6.75F);

		bodyModel[92].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 19
		bodyModel[92].setRotationPoint(12F, -20F, -4.25F);

		bodyModel[93].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 21
		bodyModel[93].setRotationPoint(12F, -20F, 3.25F);

		bodyModel[94].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 24
		bodyModel[94].setRotationPoint(15F, -33.75F, 5.25F);

		bodyModel[95].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 124
		bodyModel[95].setRotationPoint(5F, -21F, -5F);

		bodyModel[96].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 125
		bodyModel[96].setRotationPoint(5F, -30.25F, -5F);

		bodyModel[97].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 127
		bodyModel[97].setRotationPoint(5F, -30.25F, 5F);

		bodyModel[98].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 128
		bodyModel[98].setRotationPoint(17F, -21F, 5F);

		bodyModel[99].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 129
		bodyModel[99].setRotationPoint(16F, -30.25F, 5F);

		bodyModel[100].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 130
		bodyModel[100].setRotationPoint(17F, -21F, -5F);

		bodyModel[101].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 131
		bodyModel[101].setRotationPoint(16F, -30.25F, -5F);

		bodyModel[102].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 126
		bodyModel[102].setRotationPoint(5F, -21F, 5F);

		bodyModel[103].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 449
		bodyModel[103].setRotationPoint(12F, -21F, -5F);

		bodyModel[104].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 450
		bodyModel[104].setRotationPoint(16F, -21F, -5F);

		bodyModel[105].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 385
		bodyModel[105].setRotationPoint(-4F, 6F, -3F);

		bodyModel[106].addBox(0F, 0F, 0F, 8, 3, 4, 0F); // Box 386
		bodyModel[106].setRotationPoint(-12F, 6F, -2F);

		bodyModel[107].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 388
		bodyModel[107].setRotationPoint(-15F, 6F, -3F);

		bodyModel[108].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 389
		bodyModel[108].setRotationPoint(-5.15F, 5F, -5F);

		bodyModel[109].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 390
		bodyModel[109].setRotationPoint(-3.65F, 6.5F, -6F);

		bodyModel[110].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 391
		bodyModel[110].setRotationPoint(-3.15F, 7F, -5F);

		bodyModel[111].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 392
		bodyModel[111].setRotationPoint(-1.95F, 6.15F, -6.5F);

		bodyModel[112].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 393
		bodyModel[112].setRotationPoint(-4.65F, 8.75F, -6.5F);

		bodyModel[113].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 394
		bodyModel[113].setRotationPoint(-5.35F, 6.15F, -6.5F);

		bodyModel[114].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 399
		bodyModel[114].setRotationPoint(-10.5F, 6.75F, -5F);

		bodyModel[115].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 400
		bodyModel[115].setRotationPoint(-10F, 6F, -4F);

		bodyModel[116].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 405
		bodyModel[116].setRotationPoint(-12.8F, 6.15F, -6.5F);

		bodyModel[117].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 407
		bodyModel[117].setRotationPoint(-14.5F, 6.5F, -6F);

		bodyModel[118].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 408
		bodyModel[118].setRotationPoint(-14F, 7F, -5F);

		bodyModel[119].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 409
		bodyModel[119].setRotationPoint(-16.2F, 6.15F, -6.5F);

		bodyModel[120].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 414
		bodyModel[120].setRotationPoint(-10.5F, 6.75F, 4F);

		bodyModel[121].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 419
		bodyModel[121].setRotationPoint(-5.15F, 5F, 5F);

		bodyModel[122].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 420
		bodyModel[122].setRotationPoint(-4.65F, 8.75F, 5F);

		bodyModel[123].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 421
		bodyModel[123].setRotationPoint(-3.65F, 6.5F, 4.5F);

		bodyModel[124].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 422
		bodyModel[124].setRotationPoint(-1.95F, 6.15F, 5F);

		bodyModel[125].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 426
		bodyModel[125].setRotationPoint(-7F, 6F, -4F);

		bodyModel[126].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 427
		bodyModel[126].setRotationPoint(-16F, 5F, -5F);

		bodyModel[127].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 431
		bodyModel[127].setRotationPoint(-16F, 5F, 5F);

		bodyModel[128].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 432
		bodyModel[128].setRotationPoint(-14.5F, 6.5F, 4.5F);

		bodyModel[129].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 434
		bodyModel[129].setRotationPoint(-12.8F, 6.15F, 5F);

		bodyModel[130].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 435
		bodyModel[130].setRotationPoint(-16.2F, 6.15F, 5F);

		bodyModel[131].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 436
		bodyModel[131].setRotationPoint(-5.35F, 6.15F, 5F);

		bodyModel[132].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, -0.5F, -0.25F, -0.5F, 0F, -0.375F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 268
		bodyModel[132].setRotationPoint(23.5F, -21F, -8.25F);

		bodyModel[133].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, 0F, -0.375F, 0F, -0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F); // Box 269
		bodyModel[133].setRotationPoint(23.5F, -21F, 6.25F);

		bodyModel[134].addBox(0F, 0F, 0F, 8, 1, 1, 0F); // Box 64
		bodyModel[134].setRotationPoint(-31F, 6.5F, -0.5F);

		bodyModel[135].addBox(0F, 0F, 0F, 1, 2, 2, 0F); // Box 237
		bodyModel[135].setRotationPoint(-32F, 6F, -1F);

		bodyModel[136].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 275
		bodyModel[136].setRotationPoint(-30.8F, 2.75F, -0.6F);

		bodyModel[137].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0.5F); // Box 279
		bodyModel[137].setRotationPoint(-30.5F, 4F, -5.5F);

		bodyModel[138].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -0.5F); // Box 280
		bodyModel[138].setRotationPoint(-30.5F, 4F, 4.5F);

		bodyModel[139].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 285
		bodyModel[139].setRotationPoint(20.5F, 5F, -5F);

		bodyModel[140].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 286
		bodyModel[140].setRotationPoint(21.5F, 6F, -3F);

		bodyModel[141].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 287
		bodyModel[141].setRotationPoint(20.3F, 6.15F, -6.5F);

		bodyModel[142].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 289
		bodyModel[142].setRotationPoint(22F, 6.5F, -6F);

		bodyModel[143].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 290
		bodyModel[143].setRotationPoint(23.7F, 6.15F, -6.5F);

		bodyModel[144].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 292
		bodyModel[144].setRotationPoint(26F, 6.75F, -5F);

		bodyModel[145].addBox(0F, 0F, 0F, 8, 3, 4, 0F); // Box 293
		bodyModel[145].setRotationPoint(24.5F, 6F, -2F);

		bodyModel[146].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 294
		bodyModel[146].setRotationPoint(22.5F, 7F, -5F);

		bodyModel[147].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 295
		bodyModel[147].setRotationPoint(26.5F, 6F, -4F);

		bodyModel[148].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 296
		bodyModel[148].setRotationPoint(29.5F, 6F, -4F);

		bodyModel[149].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 297
		bodyModel[149].setRotationPoint(31.35F, 5F, -5F);

		bodyModel[150].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 298
		bodyModel[150].setRotationPoint(31.15F, 6.15F, -6.5F);

		bodyModel[151].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 299
		bodyModel[151].setRotationPoint(32.85F, 6.5F, -6F);

		bodyModel[152].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 300
		bodyModel[152].setRotationPoint(33.35F, 7F, -5F);

		bodyModel[153].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 301
		bodyModel[153].setRotationPoint(31.85F, 8.75F, -6.5F);

		bodyModel[154].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 302
		bodyModel[154].setRotationPoint(34.55F, 6.15F, -6.5F);

		bodyModel[155].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 304
		bodyModel[155].setRotationPoint(32.5F, 6F, -3F);

		bodyModel[156].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 305
		bodyModel[156].setRotationPoint(31.35F, 5F, 5F);

		bodyModel[157].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 306
		bodyModel[157].setRotationPoint(26F, 6.75F, 4F);

		bodyModel[158].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 308
		bodyModel[158].setRotationPoint(23.7F, 6.15F, 5F);

		bodyModel[159].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 309
		bodyModel[159].setRotationPoint(22F, 6.5F, 4.5F);

		bodyModel[160].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 310
		bodyModel[160].setRotationPoint(20.3F, 6.15F, 5F);

		bodyModel[161].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 313
		bodyModel[161].setRotationPoint(31.85F, 8.75F, 5F);

		bodyModel[162].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 314
		bodyModel[162].setRotationPoint(20.5F, 5F, 5F);

		bodyModel[163].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 315
		bodyModel[163].setRotationPoint(34.55F, 6.15F, 5F);

		bodyModel[164].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 316
		bodyModel[164].setRotationPoint(31.15F, 6.15F, 5F);

		bodyModel[165].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 317
		bodyModel[165].setRotationPoint(32.85F, 6.5F, 4.5F);

		bodyModel[166].addBox(0F, 0F, 0F, 0, 22, 4, 0F); // Box 318
		bodyModel[166].setRotationPoint(-16.4F, -17F, -8.5F);

		bodyModel[167].addBox(0F, 0F, 0F, 19, 0, 3, 0F); // Box 319
		bodyModel[167].setRotationPoint(-15F, -14.5F, -8.5F);

		bodyModel[168].addBox(0F, 0F, 0F, 19, 0, 3, 0F); // Box 320
		bodyModel[168].setRotationPoint(-16.1F, -14.5F, 6F);

		bodyModel[169].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 322
		bodyModel[169].setRotationPoint(-4.25F, 0.5F, -6F);

		bodyModel[170].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 323
		bodyModel[170].setRotationPoint(-3.5F, -5F, -8.5F);

		bodyModel[171].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 324
		bodyModel[171].setRotationPoint(-6.5F, 0F, -8.5F);

		bodyModel[172].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 325
		bodyModel[172].setRotationPoint(-2.75F, -18F, -5.5F);

		bodyModel[173].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 326
		bodyModel[173].setRotationPoint(-9.25F, 0.5F, -6F);

		bodyModel[174].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 327
		bodyModel[174].setRotationPoint(-8.5F, -5F, -8.5F);

		bodyModel[175].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 328
		bodyModel[175].setRotationPoint(-11.5F, 0F, -8.5F);

		bodyModel[176].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 329
		bodyModel[176].setRotationPoint(-7.75F, -18F, -5.5F);

		bodyModel[177].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 330
		bodyModel[177].setRotationPoint(-13.25F, 0.5F, -6F);

		bodyModel[178].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 331
		bodyModel[178].setRotationPoint(-12.5F, -5F, -8.5F);

		bodyModel[179].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 332
		bodyModel[179].setRotationPoint(-15.5F, 0F, -8.5F);

		bodyModel[180].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 333
		bodyModel[180].setRotationPoint(-11.75F, -18F, -5.5F);

		bodyModel[181].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 334
		bodyModel[181].setRotationPoint(-13.25F, 0.5F, 5F);

		bodyModel[182].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 335
		bodyModel[182].setRotationPoint(-12.5F, -5F, 4.5F);

		bodyModel[183].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 336
		bodyModel[183].setRotationPoint(-15.5F, 0F, 4.5F);

		bodyModel[184].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 337
		bodyModel[184].setRotationPoint(-11.75F, -18F, 4.5F);

		bodyModel[185].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 338
		bodyModel[185].setRotationPoint(-9.25F, 0.5F, 5F);

		bodyModel[186].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 339
		bodyModel[186].setRotationPoint(-11.5F, 0F, 4.5F);

		bodyModel[187].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 340
		bodyModel[187].setRotationPoint(-8.5F, -5F, 4.5F);

		bodyModel[188].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 341
		bodyModel[188].setRotationPoint(-6.5F, 0F, 4.5F);

		bodyModel[189].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 342
		bodyModel[189].setRotationPoint(-3.5F, -5F, 4.5F);

		bodyModel[190].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 343
		bodyModel[190].setRotationPoint(-4.25F, 0.5F, 5F);

		bodyModel[191].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 344
		bodyModel[191].setRotationPoint(-0.5F, 0F, 4.5F);

		bodyModel[192].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 345
		bodyModel[192].setRotationPoint(1.75F, 0.5F, 5F);

		bodyModel[193].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 346
		bodyModel[193].setRotationPoint(2.5F, -5F, 4.5F);

		bodyModel[194].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 347
		bodyModel[194].setRotationPoint(-7.75F, -18F, 4.5F);

		bodyModel[195].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 348
		bodyModel[195].setRotationPoint(-2.75F, -18F, 4.5F);

		bodyModel[196].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 349
		bodyModel[196].setRotationPoint(3.25F, -18F, 4.5F);

		bodyModel[197].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 376
		bodyModel[197].setRotationPoint(19.25F, -18F, 4F);

		bodyModel[198].addShapeBox(0F, 0F, 0F, 1, 2, 4, 0F,0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 379
		bodyModel[198].setRotationPoint(-23F, -6F, -3F);

		bodyModel[199].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0F, 0.1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.4F, 0F, 0F, 0.1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.4F); // Box 380
		bodyModel[199].setRotationPoint(-22.5F, -16F, -9F);

		bodyModel[200].addShapeBox(0F, 0F, 0F, 2, 1, 11, 0F,-1.3F, -0.5F, -2F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0.5F, -1.3F, -0.5F, -1F, -1.3F, 0F, -2F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, -1.3F, 0F, -1F); // Box 325
		bodyModel[200].setRotationPoint(-31.5F, -16.5F, -6F);

		bodyModel[201].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,-1.3F, 0F, -2.5F, 0F, 0F, -1F, 0F, 0F, 0.5F, -1.3F, 0F, 0.5F, -1.2F, 0F, -2.5F, 0F, 0F, -1F, 0F, 0F, 0.5F, -1.2F, 0F, 0.5F); // Box 326
		bodyModel[201].setRotationPoint(-31.5F, -15.5F, -6.5F);

		bodyModel[202].addShapeBox(0F, 0F, 0F, 1, 6, 22, 0F,0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, -13.5F, 0F, 0F, -13.7F, 0F, -4F, -0.2F, 0F, -4F, 0F, 0F, -4F, -13.5F, 0F, -4F, -13.7F); // lamp
		bodyModel[202].setRotationPoint(-30.1F, -15.5F, -4.25F);

		bodyModel[203].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,-1.3F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -1F, -1.3F, 0F, -2.5F, -1.2F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -1F, -1.2F, 0F, -2.5F); // Box 333
		bodyModel[203].setRotationPoint(-31.5F, -15.5F, 4.5F);

		bodyModel[204].addShapeBox(0F, 0F, 0F, 21, 8, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 333
		bodyModel[204].setRotationPoint(-16.5F, -3.5F, -10.1F);

		bodyModel[205].addShapeBox(0F, 0F, 0F, 21, 8, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 336
		bodyModel[205].setRotationPoint(-16.5F, -3F, 9.1F);

		bodyModel[206].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, 0.1F, 0F, -0.15F, 0.1F, 0F, 0.35F, -0.125F, 0F, 0.35F, -0.125F, 0F, -0.125F, 0.35F, 0F, -0.125F, 0.35F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // lamp
		bodyModel[206].setRotationPoint(-30.9F, -0.75F, -0.88F);

		bodyModel[207].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.35F, -0.125F, 0F, 0.35F, -0.125F, 0F, -0.15F, 0.1F, 0F, -0.15F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.35F, 0F, -0.125F, 0.35F); // lamp
		bodyModel[207].setRotationPoint(-30.9F, -0.75F, -0.129999999999999F);

		bodyModel[208].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.35F, 0F, -0.125F, 0.35F, 0F, 0.35F, -0.125F, 0F, 0.35F, -0.125F, 0F, -0.15F, 0.1F, 0F, -0.15F, 0.1F); // lamp
		bodyModel[208].setRotationPoint(-30.9F, 0F, -0.129999999999999F);

		bodyModel[209].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.35F, 0F, -0.125F, 0.35F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, 0.1F, 0F, -0.15F, 0.1F, 0F, 0.35F, -0.125F, 0F, 0.35F, -0.125F); // lamp
		bodyModel[209].setRotationPoint(-30.9F, 0F, -0.88F);

		bodyModel[210].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F); // lamp
		bodyModel[210].setRotationPoint(-30.9F, 1.75F, -0.88F);

		bodyModel[211].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F); // lamp
		bodyModel[211].setRotationPoint(-30.9F, 1.75F, -0.129999999999999F);

		bodyModel[212].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F); // lamp
		bodyModel[212].setRotationPoint(-30.9F, 1F, -0.129999999999999F);

		bodyModel[213].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // lamp
		bodyModel[213].setRotationPoint(-30.9F, 1F, -0.88F);

		bodyModel[214].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F); // lamp
		bodyModel[214].setRotationPoint(-30.9F, 0.75F, 0.5F);

		bodyModel[215].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F); // lamp
		bodyModel[215].setRotationPoint(-30.9F, 0.75F, 1.25F);

		bodyModel[216].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F); // lamp
		bodyModel[216].setRotationPoint(-30.9F, 0F, 1.25F);

		bodyModel[217].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // lamp
		bodyModel[217].setRotationPoint(-30.9F, 0F, 0.5F);

		bodyModel[218].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F); // lamp
		bodyModel[218].setRotationPoint(-30.9F, 0.75F, -2.25F);

		bodyModel[219].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F); // lamp
		bodyModel[219].setRotationPoint(-30.9F, 0.75F, -1.5F);

		bodyModel[220].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F); // lamp
		bodyModel[220].setRotationPoint(-30.9F, 0F, -1.5F);

		bodyModel[221].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // lamp
		bodyModel[221].setRotationPoint(-30.9F, 0F, -2.25F);

		bodyModel[222].addShapeBox(0F, 0F, 0F, 12, 8, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 361
		bodyModel[222].setRotationPoint(14.5F, -3.5F, -10.1F);

		bodyModel[223].addShapeBox(0F, 0F, 0F, 12, 2, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 362
		bodyModel[223].setRotationPoint(14.5F, 5F, -10.1F);

		bodyModel[224].addShapeBox(0F, 0F, 0F, 12, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.2F, 0F, 0.5F, 0.2F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 363
		bodyModel[224].setRotationPoint(14.5F, -15F, -10F);

		bodyModel[225].addShapeBox(0F, 0F, 0F, 12, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 364
		bodyModel[225].setRotationPoint(14.5F, 5F, 9.1F);

		bodyModel[226].addShapeBox(0F, 0F, 0F, 12, 8, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 365
		bodyModel[226].setRotationPoint(14.5F, -3F, 9.1F);

		bodyModel[227].addShapeBox(0F, 0F, 0F, 12, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 366
		bodyModel[227].setRotationPoint(14.5F, -14.5F, 9F);

		bodyModel[228].addShapeBox(0F, 0F, 0F, 12, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 367
		bodyModel[228].setRotationPoint(14.5F, 4F, -9F);

		bodyModel[229].addShapeBox(0F, 0F, 0F, 4, 8, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 368
		bodyModel[229].setRotationPoint(26.5F, -3F, 8.6F);

		bodyModel[230].addShapeBox(0F, 0F, 0F, 4, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 369
		bodyModel[230].setRotationPoint(26.5F, 5F, 8.6F);

		bodyModel[231].addShapeBox(0F, 0F, 0F, 4, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 370
		bodyModel[231].setRotationPoint(26.5F, -14.5F, 8.5F);

		bodyModel[232].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 371
		bodyModel[232].setRotationPoint(26.5F, -16F, 8.5F);

		bodyModel[233].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 372
		bodyModel[233].setRotationPoint(26.5F, -16F, -9.5F);

		bodyModel[234].addShapeBox(0F, 0F, 0F, 4, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.2F, 0F, 0.5F, 0.2F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 373
		bodyModel[234].setRotationPoint(26.5F, -15F, -9.5F);

		bodyModel[235].addShapeBox(0F, 0F, 0F, 4, 8, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 374
		bodyModel[235].setRotationPoint(26.5F, -3.5F, -9.6F);

		bodyModel[236].addShapeBox(0F, 0F, 0F, 4, 2, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 375
		bodyModel[236].setRotationPoint(26.5F, 5F, -9.6F);

		bodyModel[237].addShapeBox(0F, 0F, 0F, 4, 2, 2, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F); // Box 376
		bodyModel[237].setRotationPoint(26.5F, -18F, -9.5F);

		bodyModel[238].addShapeBox(0F, 0F, 0F, 4, 1, 16, 0F,0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -1F, 0F, -0.5F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 377
		bodyModel[238].setRotationPoint(26.5F, -19F, -7.5F);

		bodyModel[239].addShapeBox(0F, 0F, 0F, 4, 2, 2, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 378
		bodyModel[239].setRotationPoint(26.5F, -18F, 7.5F);

		bodyModel[240].addShapeBox(0F, 0F, 0F, 3, 2, 3, 0F,0F, -1F, 1F, 0F, -1F, 1F, 0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 3F, 0F, -1F, 3F); // Box 13
		bodyModel[240].setRotationPoint(27F, -18F, -6F);

		bodyModel[241].addShapeBox(0F, 0F, 0F, 3, 4, 1, 0F,0F, -2F, -1F, 0F, -2F, -1F, 0F, -3F, 1F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 16
		bodyModel[241].setRotationPoint(27F, -19F, -8F);

		bodyModel[242].addShapeBox(0F, 0F, 0F, 3, 4, 1, 0F,0F, -3F, 1F, 0F, -3F, 1F, 0F, -2F, -1F, 0F, -2F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 17
		bodyModel[242].setRotationPoint(27F, -19F, 7F);

		bodyModel[243].addShapeBox(0F, 0F, 0F, 3, 2, 3, 0F,0F, 0F, 3F, 0F, 0F, 3F, 0F, -1F, 1F, 0F, -1F, 1F, 0F, -1F, 3F, 0F, -1F, 3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 18
		bodyModel[243].setRotationPoint(27F, -18F, 3F);

		bodyModel[244].addBox(0F, 0F, 0F, 3, 19, 1, 0F); // Box 19
		bodyModel[244].setRotationPoint(27F, -15F, 7F);

		bodyModel[245].addBox(0F, 0F, 0F, 3, 19, 1, 0F); // Box 20
		bodyModel[245].setRotationPoint(27F, -15F, -8F);

		bodyModel[246].addBox(0F, 0F, 0F, 4, 1, 14, 0F); // Box 21
		bodyModel[246].setRotationPoint(26.5F, 3.99F, -7F);

		bodyModel[247].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F); // Box 22
		bodyModel[247].setRotationPoint(30.5F, 3.99F, -7F);

		bodyModel[248].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F); // Box 24
		bodyModel[248].setRotationPoint(30.5F, 3.99F, 0F);

		bodyModel[249].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F); // Box 25
		bodyModel[249].setRotationPoint(23.5F, 3.99F, 0F);

		bodyModel[250].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F); // Box 26
		bodyModel[250].setRotationPoint(23.5F, 3.99F, -7F);

		bodyModel[251].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 27
		bodyModel[251].setRotationPoint(27F, -4F, 5.5F);

		bodyModel[252].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 29
		bodyModel[252].setRotationPoint(27F, -4F, -6.5F);

		bodyModel[253].addShapeBox(0F, 0F, 0F, 3, 7, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 30
		bodyModel[253].setRotationPoint(27F, -3F, -6.5F);

		bodyModel[254].addShapeBox(0F, 0F, 0F, 3, 7, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 31
		bodyModel[254].setRotationPoint(27F, -3F, 5.5F);

		bodyModel[255].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F); // Box 40
		bodyModel[255].setRotationPoint(25F, -3F, -6.5F);

		bodyModel[256].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F); // Box 41
		bodyModel[256].setRotationPoint(30F, -3F, -6F);

		bodyModel[257].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F); // Box 42
		bodyModel[257].setRotationPoint(25F, -3F, 5.5F);

		bodyModel[258].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F); // Box 43
		bodyModel[258].setRotationPoint(30F, -3F, 5F);

		bodyModel[259].addBox(0F, 0F, 0F, 1, 19, 2, 0F); // Box 61
		bodyModel[259].setRotationPoint(25.5F, -15F, 7F);

		bodyModel[260].addBox(0F, 0F, 0F, 1, 19, 2, 0F); // Box 88
		bodyModel[260].setRotationPoint(25.5F, -15F, -9F);

		bodyModel[261].addShapeBox(0F, 0F, 0F, 1, 4, 2, 0F,0F, -3F, 1F, 0F, -3F, 1F, 0F, -1F, -0.5F, 0F, -1F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 172
		bodyModel[261].setRotationPoint(25.5F, -19F, 7F);

		bodyModel[262].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 2.5F, 0F, 0F, 2.5F, 0F, -1F, 3F, 0F, -1F, 3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 176
		bodyModel[262].setRotationPoint(25.5F, -18F, 3F);

		bodyModel[263].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 2.5F, 0F, 0F, 2.5F, 0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 3F, 0F, -1F, 3F); // Box 404
		bodyModel[263].setRotationPoint(25.5F, -18F, -6F);

		bodyModel[264].addShapeBox(0F, 0F, 0F, 1, 4, 2, 0F,0F, -1F, -0.5F, 0F, -1F, -0.5F, 0F, -3F, 1F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 405
		bodyModel[264].setRotationPoint(25.5F, -19F, -9F);

		bodyModel[265].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 406
		bodyModel[265].setRotationPoint(17.75F, 0.5F, -6F);

		bodyModel[266].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 407
		bodyModel[266].setRotationPoint(18.5F, -5F, -8.5F);

		bodyModel[267].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 408
		bodyModel[267].setRotationPoint(20.5F, 0F, -8.5F);

		bodyModel[268].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 409
		bodyModel[268].setRotationPoint(22.75F, 0.5F, -6F);

		bodyModel[269].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 410
		bodyModel[269].setRotationPoint(23.5F, -5F, -8.5F);

		bodyModel[270].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 411
		bodyModel[270].setRotationPoint(19.25F, -18F, -5.5F);

		bodyModel[271].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 412
		bodyModel[271].setRotationPoint(24.25F, -18F, -5.5F);

		bodyModel[272].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 413
		bodyModel[272].setRotationPoint(15.5F, 0F, 4.5F);

		bodyModel[273].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 414
		bodyModel[273].setRotationPoint(18.5F, -5F, 4.5F);

		bodyModel[274].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 415
		bodyModel[274].setRotationPoint(17.75F, 0.5F, 5F);

		bodyModel[275].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 416
		bodyModel[275].setRotationPoint(23.5F, -5F, 4.5F);

		bodyModel[276].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 417
		bodyModel[276].setRotationPoint(20.5F, 0F, 4.5F);

		bodyModel[277].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 418
		bodyModel[277].setRotationPoint(22.75F, 0.5F, 5F);

		bodyModel[278].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 419
		bodyModel[278].setRotationPoint(15.5F, 0F, -8.5F);

		bodyModel[279].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 420
		bodyModel[279].setRotationPoint(24.25F, -18F, 4F);

		bodyModel[280].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.5F, 0F, 0F, 0.5F, 0F, 1F, 0.5F, 0F, -1F, 0.2F, 0F, 1F, -0.5F, -0.75F, 0F, 0.5F, -0.75F, 1F, 0.5F, -0.75F, -1F, 0.2F, -0.75F, 1F); // Box 367
		bodyModel[280].setRotationPoint(-30F, 4F, -5.51F);

		bodyModel[281].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, -1.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 1.5F, 0F, -0.75F, -1.5F, 0F, -0.75F, -0.5F, 0F, -0.75F, 0.5F, 0F, -0.75F, 1.5F); // Box 368
		bodyModel[281].setRotationPoint(-28.5F, 4F, -8.03F);

		bodyModel[282].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -1.5F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 1.5F, 0F, -0.75F, -1.5F, 0F, -0.75F, 0.1F, 0F, -0.75F, 0F, 0F, -0.75F, 1.5F); // Box 369
		bodyModel[282].setRotationPoint(-26.5F, 4F, -9.01F);

		bodyModel[283].addShapeBox(0F, 0F, 0F, 21, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 376
		bodyModel[283].setRotationPoint(-16.51F, 4F, 9.22F);

		bodyModel[284].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 377
		bodyModel[284].setRotationPoint(14.49F, 4F, 9.22F);

		bodyModel[285].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 378
		bodyModel[285].setRotationPoint(26.49F, 4F, 8.71F);

		bodyModel[286].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 379
		bodyModel[286].setRotationPoint(26.49F, 4F, -9.71F);

		bodyModel[287].addShapeBox(0F, 0F, 0F, 48, 1, 5, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 377
		bodyModel[287].setRotationPoint(-21.5F, -20F, -8F);

		bodyModel[288].addShapeBox(0F, 0F, 0F, 48, 1, 5, 0F,0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 378
		bodyModel[288].setRotationPoint(-21.5F, -20F, 3F);

		bodyModel[289].addShapeBox(0F, 0F, 0F, 48, 1, 6, 0F,0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 379
		bodyModel[289].setRotationPoint(-21.5F, -20F, -3F);

		bodyModel[290].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0F, -1.5F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -1.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F); // Box 380
		bodyModel[290].setRotationPoint(-26.5F, -20F, -3F);

		bodyModel[291].addShapeBox(0F, 0F, 0F, 5, 1, 5, 0F,0F, -1.5F, 0F, 0F, -0.4F, 0F, 0F, -1F, 0F, 0F, -1.5F, -1.5F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, -1.5F); // Box 381
		bodyModel[291].setRotationPoint(-26.5F, -20F, 3F);

		bodyModel[292].addShapeBox(0F, 0F, 0F, 5, 1, 5, 0F,0F, -1.5F, -1.5F, 0F, -1F, 0F, 0F, -0.4F, 0F, 0F, -1.5F, 0F, 0F, 0.5F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F); // Box 382
		bodyModel[292].setRotationPoint(-26.5F, -20F, -8F);

		bodyModel[293].addShapeBox(0F, 0F, 0F, 21, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 313
		bodyModel[293].setRotationPoint(-16.51F, 4F, -10.21F);

		bodyModel[294].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 314
		bodyModel[294].setRotationPoint(14.49F, 4F, -10.21F);

		bodyModel[295].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, 1.5F, 0.01F, 0F, 0.5F, 0.01F, 0F, -0.5F, 0F, 0F, -1.5F, 0F, -0.75F, 1.5F, 0.01F, -0.75F, 0.5F, 0.01F, -0.75F, -0.5F, 0F, -0.75F, -1.5F); // Box 315
		bodyModel[295].setRotationPoint(-28.5F, 4F, 7.01F);

		bodyModel[296].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.2F, 0F, 1F, 0.5F, 0F, -1F, 0.5F, 0F, 1F, -0.5F, 0F, 0F, 0.2F, -0.75F, 1F, 0.5F, -0.75F, -1F, 0.5F, -0.75F, 1F, -0.5F, -0.75F, 0F); // Box 316
		bodyModel[296].setRotationPoint(-30F, 4F, 4.51F);

		bodyModel[297].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 317
		bodyModel[297].setRotationPoint(-30.5F, 2.5F, -5.25F);

		bodyModel[298].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 319
		bodyModel[298].setRotationPoint(-30.5F, 2.5F, 4F);

		bodyModel[299].addShapeBox(0F, 0F, 0F, 2, 1, 11, 0F,-1.2F, -0.5F, -2F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0.5F, -1.2F, -0.5F, -1F, -1.2F, 0.1F, -2F, 0F, 0.1F, -0.5F, 0F, 0.1F, 0.5F, -1.2F, 0.1F, -1F); // Box 110
		bodyModel[299].setRotationPoint(-31.5F, -14F, -6F);

		bodyModel[300].addShapeBox(0F, 0F, 0F, 2, 8, 11, 0F,-1.2F, -0.1F, -1.5F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -1.2F, -0.1F, -1.5F, -0.8F, 0.5F, -1.5F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.8F, 0.5F, -1.5F); // Box 111
		bodyModel[300].setRotationPoint(-31.5F, -13F, -5.5F);

		bodyModel[301].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 318
		bodyModel[301].setRotationPoint(26.5F, -19.5F, 2.5F);

		bodyModel[302].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 319
		bodyModel[302].setRotationPoint(26.5F, -19.5F, -2.5F);

		bodyModel[303].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 320
		bodyModel[303].setRotationPoint(26.5F, -19.5F, -7.5F);

		bodyModel[304].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.35F, -0.7F, -0.6F, -0.35F, -0.7F, -0.6F, -0.25F, -0.5F, 0F, -0.25F, -0.5F, 0F, -0.35F, -0.7F, -0.6F, -0.35F, -0.7F, -0.6F, -0.25F, -0.5F, 0F, -0.25F, -0.5F, 0F); // Box 321
		bodyModel[304].setRotationPoint(-16.75F, -13F, -11F);

		bodyModel[305].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.25F, -0.5F, 0F, -0.25F, -0.5F, 0F, -0.35F, -0.7F, -0.6F, -0.35F, -0.7F, -0.6F, -0.25F, -0.5F, 0F, -0.25F, -0.5F, 0F, -0.35F, -0.7F, -0.6F, -0.35F, -0.7F, -0.6F); // Box 322
		bodyModel[305].setRotationPoint(-16.75F, -13F, 10F);

		bodyModel[306].addShapeBox(0F, 0F, 0F, 5, 7, 0, 0F,0F, 0F, 0F, 0F, 0F, 3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 3F, 0F, 0F, -3F, 0F, 0F, 0F); // Box 336
		bodyModel[306].setRotationPoint(-32.5F, -15F, 10F);

		bodyModel[307].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0.25F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F); // Box 337
		bodyModel[307].setRotationPoint(-31.6F, -10F, 8.8F);

		bodyModel[308].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, -0.75F, 0F, 0F, -1F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -1F, 0F, 0F, 0.25F, 0F, 0F); // Box 325
		bodyModel[308].setRotationPoint(-25.5F, -10F, -9.5F);

		bodyModel[309].addShapeBox(0F, 0F, 0F, 5, 7, 0, 0F,0F, 0F, 0F, -8F, 0F, -3F, -8F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, 0F, -8F, 0F, -3F, -8F, 0F, 3F, 0F, 0F, 0F); // Box 326
		bodyModel[309].setRotationPoint(-25.5F, -15F, -9.3F);

		bodyModel[310].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.6F, -0.6F, 0F, -0.5F, -0.4F, 0F, -0.3F, 0F, 0F, -0.5F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 30
		bodyModel[310].setRotationPoint(-30.2F, 0.5F, 4F);

		bodyModel[311].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.5F, 0F, 0F, -0.3F, 0F, 0F, -0.5F, -0.4F, 0F, -0.6F, -0.6F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, -0.2F, 0F, 0F, -0.5F); // Box 31
		bodyModel[311].setRotationPoint(-30.2F, 0.5F, 5F);

		bodyModel[312].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, -0.2F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, -0.3F, 0F, 0F, -0.5F, -0.4F, 0F, -0.6F, -0.6F); // Box 32
		bodyModel[312].setRotationPoint(-30.2F, 2.5F, 5F);

		bodyModel[313].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.6F, -0.6F, 0F, -0.5F, -0.4F, 0F, -0.3F, 0F, 0F, -0.5F, 0F); // Box 33
		bodyModel[313].setRotationPoint(-30.2F, 2.5F, 4F);

		bodyModel[314].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, -0.2F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, -0.2F, 0F, 0F, -0.5F); // Box 331
		bodyModel[314].setRotationPoint(-30.2F, 1.5F, 5F);

		bodyModel[315].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 332
		bodyModel[315].setRotationPoint(-30.2F, 1.5F, 4F);

		bodyModel[316].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.5F, 0F, 0F, -0.3F, 0F, 0F, -0.5F, -0.4F, 0F, -0.6F, -0.6F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.3F); // Box 336
		bodyModel[316].setRotationPoint(-30.2F, 0.5F, -5F);

		bodyModel[317].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.3F); // Box 337
		bodyModel[317].setRotationPoint(-30.2F, 1.5F, -5F);

		bodyModel[318].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.4F, 0F, -0.5F, 0F, 0F, -0.3F, 0F, 0F, -0.5F, -0.4F, 0F, -0.6F, -0.6F); // Box 338
		bodyModel[318].setRotationPoint(-30.2F, 2.5F, -5F);

		bodyModel[319].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 340
		bodyModel[319].setRotationPoint(-30.3F, 0.9F, 4.4F);

		bodyModel[320].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 341
		bodyModel[320].setRotationPoint(-30.3F, 1.8F, 4.4F);

		bodyModel[321].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 342
		bodyModel[321].setRotationPoint(-30.3F, 0.9F, -5.6F);

		bodyModel[322].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 343
		bodyModel[322].setRotationPoint(-30.3F, 1.8F, -5.6F);

		bodyModel[323].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.6F, -0.6F, 0F, -0.5F, -0.4F, 0F, -0.3F, 0F, 0F, -0.5F, 0F, 0F, 0F, -0.5F, 0.1F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 344
		bodyModel[323].setRotationPoint(-30.2F, 0.5F, -6F);

		bodyModel[324].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.5F, 0.1F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.1F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 345
		bodyModel[324].setRotationPoint(-30.2F, 1.5F, -6F);

		bodyModel[325].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.5F, 0.1F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.6F, -0.6F, 0F, -0.5F, -0.4F, 0F, -0.3F, 0F, 0F, -0.5F, 0F); // Box 346
		bodyModel[325].setRotationPoint(-30.2F, 2.5F, -6F);

		bodyModel[326].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 8
		bodyModel[326].setRotationPoint(-10F, -33.75F, -4.75F);

		bodyModel[327].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 9
		bodyModel[327].setRotationPoint(-8F, -33.75F, -4.75F);

		bodyModel[328].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 10
		bodyModel[328].setRotationPoint(-10F, -32.75F, -5F);

		bodyModel[329].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 11
		bodyModel[329].setRotationPoint(-10F, -32.75F, 4F);

		bodyModel[330].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 12
		bodyModel[330].setRotationPoint(-8F, -33.75F, -6.75F);

		bodyModel[331].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 13
		bodyModel[331].setRotationPoint(-10F, -33.75F, 5.25F);

		bodyModel[332].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 15
		bodyModel[332].setRotationPoint(-10F, -33.75F, -6.75F);

		bodyModel[333].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 19
		bodyModel[333].setRotationPoint(-13F, -20F, -4.25F);

		bodyModel[334].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 21
		bodyModel[334].setRotationPoint(-13F, -20F, 3.25F);

		bodyModel[335].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 24
		bodyModel[335].setRotationPoint(-8F, -33.75F, 5.25F);

		bodyModel[336].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 449
		bodyModel[336].setRotationPoint(-11F, -21F, -5F);

		bodyModel[337].addShapeBox(-9F, -1F, -1F, 9, 1, 1, 0F,0F, 4F, 0F, 0F, -1F, 0F, 0F, -1F, 1F, 0F, 4F, 1F, 0F, -4F, 0F, 0F, 1F, 0F, 0F, 1F, 1F, 0F, -4F, 1F); // Box 58
		bodyModel[337].setRotationPoint(-11F, -21F, 0F);

		bodyModel[338].addShapeBox(1F, -1F, -3F, 19, 1, 12, 0F,1F, -2F, -1F, -9F, 4.5F, 1.5F, -9F, 4.5F, -4.5F, 1F, -2F, -7F, 1F, 2F, -1F, -9F, -4.5F, 1.5F, -9F, -4.5F, -4.5F, 1F, 2F, -7F); // Box 59
		bodyModel[338].setRotationPoint(-20.5F, -27F, 0F);

		bodyModel[339].addShapeBox(0F, 0F, 0F, 10, 3, 2, 0F,0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F); // Box 360
		bodyModel[339].setRotationPoint(-16F, -19F, -10F);

		bodyModel[340].addShapeBox(0F, 0F, 0F, 1, 20, 9, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 356
		bodyModel[340].setRotationPoint(-23F, -16F, -8F);

		bodyModel[341].addShapeBox(0F, 0F, 0F, 1, 20, 3, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -3.5F, 0F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -3.5F, 0F, 0F, 3F, 0F, 0F); // Box 357
		bodyModel[341].setRotationPoint(-23F, -16F, 1F);

		bodyModel[342].addShapeBox(0F, 0F, 0F, 1, 20, 3, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -1.5F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -1.5F, 0F, 0F, 1F, 0F, 0F); // Box 358
		bodyModel[342].setRotationPoint(-26F, -16F, 4F);

		bodyModel[343].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 401
		bodyModel[343].setRotationPoint(-11.6F, 7.5F, -6.5F);

		bodyModel[344].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 410
		bodyModel[344].setRotationPoint(-11.6F, 7.5F, 5.5F);

		bodyModel[345].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 291
		bodyModel[345].setRotationPoint(25.8F, 7.5F, -6.5F);

		bodyModel[346].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 311
		bodyModel[346].setRotationPoint(25.8F, 7.5F, 5.5F);

		bodyModel[347].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 24
		bodyModel[347].setRotationPoint(0.5F, 8.25F, -6F);

		bodyModel[348].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 458
		bodyModel[348].setRotationPoint(-17.5F, 8.25F, -6F);

		bodyModel[349].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 284
		bodyModel[349].setRotationPoint(19F, 8.25F, -6F);

		bodyModel[350].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 303
		bodyModel[350].setRotationPoint(37F, 8.25F, -6F);

		bodyModel[351].addShapeBox(0F, 0F, 0F, 11, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 406
		bodyModel[351].setRotationPoint(-15.5F, 8.75F, -6.5F);

		bodyModel[352].addShapeBox(0F, 0F, 0F, 11, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 433
		bodyModel[352].setRotationPoint(-15.5F, 8.75F, 5F);

		bodyModel[353].addShapeBox(0F, 0F, 0F, 11, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 288
		bodyModel[353].setRotationPoint(21F, 8.75F, -6.5F);

		bodyModel[354].addShapeBox(0F, 0F, 0F, 11, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 307
		bodyModel[354].setRotationPoint(21F, 8.75F, 5F);

		bodyModel[355].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -1.95F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 2F, 0F, -0.75F, -1.95F, 0F, -0.75F, 0.1F, 0F, -0.75F, 0F, 0F, -0.75F, 2F); // Box 615
		bodyModel[355].setRotationPoint(-26.5F, -16.1F, -9.51F);

		bodyModel[356].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 0.2F, 0F, 0F, 0.7F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.75F, 0.2F, 0F, -0.75F, 0.7F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F); // Box 617
		bodyModel[356].setRotationPoint(-21.5F, -16.1F, -9.41F);

		bodyModel[357].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 618
		bodyModel[357].setRotationPoint(-16.5F, -16.1F, -10.11F);

		bodyModel[358].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.4F, 0F, 0F, 0.5F, 0F, 1.1F, 0.5F, 0F, -1F, 0.3F, 0F, 0.5F, -0.4F, -0.75F, 0F, 0.5F, -0.75F, 1.1F, 0.5F, -0.75F, -1F, 0.3F, -0.75F, 0.5F); // Box 414
		bodyModel[358].setRotationPoint(-30F, -16.1F, -5.5F);

		bodyModel[359].addShapeBox(0F, 0F, 0F, 1, 1, 7, 0F,-0.1F, 0F, 0.45F, -0.6F, 0F, 1.05F, -0.6F, 0F, 1.05F, -0.1F, 0F, 0.45F, -0.1F, -0.75F, 0.45F, -0.6F, -0.75F, 1.05F, -0.6F, -0.75F, 1.05F, -0.1F, -0.75F, 0.45F); // Box 417
		bodyModel[359].setRotationPoint(-30.4F, -16.1F, -3.5F);

		bodyModel[360].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, -1.9F, 0F, 0F, -0.95F, 0F, 0F, 0.3F, 0F, 0F, 1.75F, 0F, -0.75F, -1.9F, 0F, -0.75F, -0.95F, 0F, -0.75F, 0.3F, 0F, -0.75F, 1.75F); // Box 411
		bodyModel[360].setRotationPoint(-28.5F, -16.1F, -8.51F);

		bodyModel[361].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.3F, 0F, 0.5F, 0.5F, 0F, -1F, 0.5F, 0F, 1.1F, -0.4F, 0F, 0F, 0.3F, -0.75F, 0.5F, 0.5F, -0.75F, -1F, 0.5F, -0.75F, 1.1F, -0.4F, -0.75F, 0F); // Box 366
		bodyModel[361].setRotationPoint(-30F, -16.1F, 4.5F);

		bodyModel[362].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, 1.75F, 0F, 0F, 0.3F, 0F, 0F, -0.95F, 0F, 0F, -1.9F, 0F, -0.75F, 1.75F, 0F, -0.75F, 0.3F, 0F, -0.75F, -0.95F, 0F, -0.75F, -1.9F); // Box 367
		bodyModel[362].setRotationPoint(-28.5F, -16.1F, 7.49F);

		bodyModel[363].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 2F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, -1.95F, 0F, -0.75F, 2F, 0F, -0.75F, 0F, 0F, -0.75F, 0.1F, 0F, -0.75F, -1.95F); // Box 368
		bodyModel[363].setRotationPoint(-26.5F, -16.1F, 8.49F);

		bodyModel[364].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.7F, 0F, 0F, 0.2F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F, 0F, -0.75F, 0.7F, 0F, -0.75F, 0.2F); // Box 369
		bodyModel[364].setRotationPoint(-21.5F, -16.1F, 8.39F);

		bodyModel[365].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 370
		bodyModel[365].setRotationPoint(-16.5F, -16.1F, 9.09F);

		bodyModel[366].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 366
		bodyModel[366].setRotationPoint(18F, -21F, -5F);

		bodyModel[367].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 367
		bodyModel[367].setRotationPoint(16F, -20F, 3.25F);

		bodyModel[368].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 368
		bodyModel[368].setRotationPoint(16F, -20F, -4.25F);

		bodyModel[369].addShapeBox(-9F, -1F, -1F, 9, 1, 1, 0F,0F, 4F, 0F, 0F, -1F, 0F, 0F, -1F, 1F, 0F, 4F, 1F, 0F, -4F, 0F, 0F, 1F, 0F, 0F, 1F, 1F, 0F, -4F, 1F); // Box 369
		bodyModel[369].setRotationPoint(18F, -21F, 0F);

		bodyModel[370].addShapeBox(1F, -1F, -3F, 19, 1, 12, 0F,1F, -2F, -1F, -9F, 4.5F, 1.5F, -9F, 4.5F, -4.5F, 1F, -2F, -7F, 1F, 2F, -1F, -9F, -4.5F, 1.5F, -9F, -4.5F, -4.5F, 1F, 2F, -7F); // Box 370
		bodyModel[370].setRotationPoint(8.5F, -27F, 0F);

		bodyModel[371].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 371
		bodyModel[371].setRotationPoint(19F, -32.75F, 4F);

		bodyModel[372].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 372
		bodyModel[372].setRotationPoint(19F, -33.75F, 5.25F);

		bodyModel[373].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 373
		bodyModel[373].setRotationPoint(21F, -33.75F, 5.25F);

		bodyModel[374].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 374
		bodyModel[374].setRotationPoint(21F, -33.75F, -4.75F);

		bodyModel[375].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 375
		bodyModel[375].setRotationPoint(21F, -33.75F, -6.75F);

		bodyModel[376].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 376
		bodyModel[376].setRotationPoint(19F, -32.75F, -5F);

		bodyModel[377].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 377
		bodyModel[377].setRotationPoint(19F, -33.75F, -6.75F);

		bodyModel[378].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 378
		bodyModel[378].setRotationPoint(19F, -33.75F, -4.75F);
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		for (int i = 0; i < 379; i++) {
			if (bodyModel[i].boxName != null && bodyModel[i].boxName.contains("lamp") ) {
				Minecraft.getMinecraft().entityRenderer.disableLightmap(1D);
				bodyModel[i].render(f5);
				Minecraft.getMinecraft().entityRenderer.enableLightmap(1D);
			}else if (bodyModel[i].boxName != null && bodyModel[i].boxName.contains("cull")) {
				GL11.glDisable(GL11.GL_CULL_FACE);
				bodyModel[i].render(f5);
				GL11.glEnable(GL11.GL_CULL_FACE);
			} else {
				bodyModel[i].render(f5);
			}
		}
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
	}

}