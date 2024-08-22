//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2023 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 
// Model Creator: 
// Created on: 14.10.2021 - 00:55:19
// Last changed on: 14.10.2021 - 00:55:19

package train.client.render.models;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import fexcraft.tmt.slim.ModelConverter;
import fexcraft.tmt.slim.ModelRendererTurbo;

public class ModelClass442DTS extends ModelConverter //Same as Filename
{
	int textureX = 512;
	int textureY = 512;

	public ModelClass442DTS() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[378];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);


		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // 01
		bodyModel[1] = new ModelRendererTurbo(this, 209, 1, textureX, textureY); // 02
		bodyModel[2] = new ModelRendererTurbo(this, 209, 9, textureX, textureY); // 03
		bodyModel[3] = new ModelRendererTurbo(this, 233, 17, textureX, textureY); // 04
		bodyModel[4] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // 05
		bodyModel[5] = new ModelRendererTurbo(this, 193, 33, textureX, textureY); // 06
		bodyModel[6] = new ModelRendererTurbo(this, 1, 41, textureX, textureY); // 07
		bodyModel[7] = new ModelRendererTurbo(this, 185, 41, textureX, textureY); // 08
		bodyModel[8] = new ModelRendererTurbo(this, 1, 49, textureX, textureY); // 09
		bodyModel[9] = new ModelRendererTurbo(this, 185, 49, textureX, textureY); // 10
		bodyModel[10] = new ModelRendererTurbo(this, 8, 65, textureX, textureY); // 11
		bodyModel[11] = new ModelRendererTurbo(this, 425, 1, textureX, textureY); // 12
		bodyModel[12] = new ModelRendererTurbo(this, 457, 9, textureX, textureY); // 13
		bodyModel[13] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // 14
		bodyModel[14] = new ModelRendererTurbo(this, 401, 1, textureX, textureY); // 15
		bodyModel[15] = new ModelRendererTurbo(this, 417, 1, textureX, textureY); // 16
		bodyModel[16] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // 17
		bodyModel[17] = new ModelRendererTurbo(this, 433, 1, textureX, textureY); // 18
		bodyModel[18] = new ModelRendererTurbo(this, 377, 33, textureX, textureY); // 19
		bodyModel[19] = new ModelRendererTurbo(this, 441, 1, textureX, textureY); // 20
		bodyModel[20] = new ModelRendererTurbo(this, 473, 1, textureX, textureY); // 21
		bodyModel[21] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // 22
		bodyModel[22] = new ModelRendererTurbo(this, 505, 17, textureX, textureY); // 23
		bodyModel[23] = new ModelRendererTurbo(this, 385, 33, textureX, textureY); // 24
		bodyModel[24] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // 25
		bodyModel[25] = new ModelRendererTurbo(this, 9, 1, textureX, textureY); // 26
		bodyModel[26] = new ModelRendererTurbo(this, 409, 1, textureX, textureY); // 27
		bodyModel[27] = new ModelRendererTurbo(this, 417, 1, textureX, textureY); // 28
		bodyModel[28] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // 29
		bodyModel[29] = new ModelRendererTurbo(this, 481, 1, textureX, textureY); // 30
		bodyModel[30] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // 31
		bodyModel[31] = new ModelRendererTurbo(this, 505, 1, textureX, textureY); // 32
		bodyModel[32] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // 33
		bodyModel[33] = new ModelRendererTurbo(this, 401, 9, textureX, textureY); // 34
		bodyModel[34] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // 35
		bodyModel[35] = new ModelRendererTurbo(this, 433, 9, textureX, textureY); // 36
		bodyModel[36] = new ModelRendererTurbo(this, 209, 17, textureX, textureY); // 37
		bodyModel[37] = new ModelRendererTurbo(this, 489, 25, textureX, textureY); // 64
		bodyModel[38] = new ModelRendererTurbo(this, 425, 49, textureX, textureY); // 65
		bodyModel[39] = new ModelRendererTurbo(this, 457, 49, textureX, textureY); // 66
		bodyModel[40] = new ModelRendererTurbo(this, 1, 57, textureX, textureY); // 67
		bodyModel[41] = new ModelRendererTurbo(this, 409, 49, textureX, textureY); // 68
		bodyModel[42] = new ModelRendererTurbo(this, 17, 57, textureX, textureY); // 69
		bodyModel[43] = new ModelRendererTurbo(this, 33, 57, textureX, textureY); // 70
		bodyModel[44] = new ModelRendererTurbo(this, 49, 57, textureX, textureY); // 71
		bodyModel[45] = new ModelRendererTurbo(this, 65, 57, textureX, textureY); // 72
		bodyModel[46] = new ModelRendererTurbo(this, 89, 57, textureX, textureY); // 73
		bodyModel[47] = new ModelRendererTurbo(this, 209, 65, textureX, textureY); // 74
		bodyModel[48] = new ModelRendererTurbo(this, 249, 65, textureX, textureY); // 75
		bodyModel[49] = new ModelRendererTurbo(this, 409, 33, textureX, textureY); // 76
		bodyModel[50] = new ModelRendererTurbo(this, 425, 33, textureX, textureY); // 77
		bodyModel[51] = new ModelRendererTurbo(this, 377, 41, textureX, textureY); // 78
		bodyModel[52] = new ModelRendererTurbo(this, 377, 49, textureX, textureY); // 79
		bodyModel[53] = new ModelRendererTurbo(this, 113, 57, textureX, textureY); // 80
		bodyModel[54] = new ModelRendererTurbo(this, 129, 57, textureX, textureY); // 81
		bodyModel[55] = new ModelRendererTurbo(this, 145, 57, textureX, textureY); // 82
		bodyModel[56] = new ModelRendererTurbo(this, 161, 57, textureX, textureY); // 83
		bodyModel[57] = new ModelRendererTurbo(this, 497, 49, textureX, textureY); // 84
		bodyModel[58] = new ModelRendererTurbo(this, 177, 57, textureX, textureY); // 85
		bodyModel[59] = new ModelRendererTurbo(this, 193, 65, textureX, textureY); // 86
		bodyModel[60] = new ModelRendererTurbo(this, 233, 65, textureX, textureY); // 87
		bodyModel[61] = new ModelRendererTurbo(this, 249, 65, textureX, textureY); // 88
		bodyModel[62] = new ModelRendererTurbo(this, 273, 65, textureX, textureY); // 89
		bodyModel[63] = new ModelRendererTurbo(this, 273, 65, textureX, textureY); // 90
		bodyModel[64] = new ModelRendererTurbo(this, 305, 65, textureX, textureY); // 91
		bodyModel[65] = new ModelRendererTurbo(this, 329, 65, textureX, textureY); // 92
		bodyModel[66] = new ModelRendererTurbo(this, 401, 57, textureX, textureY); // 93
		bodyModel[67] = new ModelRendererTurbo(this, 417, 57, textureX, textureY); // 94
		bodyModel[68] = new ModelRendererTurbo(this, 497, 57, textureX, textureY); // 95
		bodyModel[69] = new ModelRendererTurbo(this, 1, 65, textureX, textureY); // 96
		bodyModel[70] = new ModelRendererTurbo(this, 217, 65, textureX, textureY); // 97
		bodyModel[71] = new ModelRendererTurbo(this, 297, 65, textureX, textureY); // 98
		bodyModel[72] = new ModelRendererTurbo(this, 369, 65, textureX, textureY); // 99
		bodyModel[73] = new ModelRendererTurbo(this, 425, 65, textureX, textureY); // 100
		bodyModel[74] = new ModelRendererTurbo(this, 441, 65, textureX, textureY); // 101
		bodyModel[75] = new ModelRendererTurbo(this, 313, 65, textureX, textureY); // 102
		bodyModel[76] = new ModelRendererTurbo(this, 457, 65, textureX, textureY); // 103
		bodyModel[77] = new ModelRendererTurbo(this, 497, 65, textureX, textureY); // 104
		bodyModel[78] = new ModelRendererTurbo(this, 233, 73, textureX, textureY); // 105
		bodyModel[79] = new ModelRendererTurbo(this, 465, 65, textureX, textureY); // 106
		bodyModel[80] = new ModelRendererTurbo(this, 249, 73, textureX, textureY); // 107
		bodyModel[81] = new ModelRendererTurbo(this, 329, 73, textureX, textureY); // 108
		bodyModel[82] = new ModelRendererTurbo(this, 257, 73, textureX, textureY); // 109
		bodyModel[83] = new ModelRendererTurbo(this, 273, 73, textureX, textureY); // 110
		bodyModel[84] = new ModelRendererTurbo(this, 281, 73, textureX, textureY); // 111
		bodyModel[85] = new ModelRendererTurbo(this, 297, 73, textureX, textureY); // 112
		bodyModel[86] = new ModelRendererTurbo(this, 313, 73, textureX, textureY); // 113
		bodyModel[87] = new ModelRendererTurbo(this, 417, 73, textureX, textureY); // 117
		bodyModel[88] = new ModelRendererTurbo(this, 433, 73, textureX, textureY); // 118
		bodyModel[89] = new ModelRendererTurbo(this, 1, 81, textureX, textureY); // 119
		bodyModel[90] = new ModelRendererTurbo(this, 17, 81, textureX, textureY); // 120
		bodyModel[91] = new ModelRendererTurbo(this, 369, 73, textureX, textureY); // 121
		bodyModel[92] = new ModelRendererTurbo(this, 401, 73, textureX, textureY); // 122
		bodyModel[93] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // 123
		bodyModel[94] = new ModelRendererTurbo(this, 49, 81, textureX, textureY); // 124
		bodyModel[95] = new ModelRendererTurbo(this, 65, 81, textureX, textureY); // 125
		bodyModel[96] = new ModelRendererTurbo(this, 89, 81, textureX, textureY); // 126
		bodyModel[97] = new ModelRendererTurbo(this, 433, 73, textureX, textureY); // 127
		bodyModel[98] = new ModelRendererTurbo(this, 97, 81, textureX, textureY); // 128
		bodyModel[99] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // 129
		bodyModel[100] = new ModelRendererTurbo(this, 121, 81, textureX, textureY); // 130
		bodyModel[101] = new ModelRendererTurbo(this, 129, 81, textureX, textureY); // 131
		bodyModel[102] = new ModelRendererTurbo(this, 137, 81, textureX, textureY); // 132
		bodyModel[103] = new ModelRendererTurbo(this, 145, 81, textureX, textureY); // 133
		bodyModel[104] = new ModelRendererTurbo(this, 161, 81, textureX, textureY); // 134
		bodyModel[105] = new ModelRendererTurbo(this, 177, 81, textureX, textureY); // 135
		bodyModel[106] = new ModelRendererTurbo(this, 193, 81, textureX, textureY); // 136
		bodyModel[107] = new ModelRendererTurbo(this, 289, 81, textureX, textureY); // 137
		bodyModel[108] = new ModelRendererTurbo(this, 297, 81, textureX, textureY); // 138
		bodyModel[109] = new ModelRendererTurbo(this, 305, 81, textureX, textureY); // 139
		bodyModel[110] = new ModelRendererTurbo(this, 321, 81, textureX, textureY); // 140
		bodyModel[111] = new ModelRendererTurbo(this, 353, 81, textureX, textureY); // 141
		bodyModel[112] = new ModelRendererTurbo(this, 369, 81, textureX, textureY); // 142
		bodyModel[113] = new ModelRendererTurbo(this, 369, 81, textureX, textureY); // 143
		bodyModel[114] = new ModelRendererTurbo(this, 401, 81, textureX, textureY); // 144
		bodyModel[115] = new ModelRendererTurbo(this, 1, 89, textureX, textureY); // 145
		bodyModel[116] = new ModelRendererTurbo(this, 393, 81, textureX, textureY); // 146
		bodyModel[117] = new ModelRendererTurbo(this, 401, 81, textureX, textureY); // 147
		bodyModel[118] = new ModelRendererTurbo(this, 425, 81, textureX, textureY); // 148
		bodyModel[119] = new ModelRendererTurbo(this, 433, 81, textureX, textureY); // 149
		bodyModel[120] = new ModelRendererTurbo(this, 441, 81, textureX, textureY); // 150
		bodyModel[121] = new ModelRendererTurbo(this, 41, 89, textureX, textureY); // 151
		bodyModel[122] = new ModelRendererTurbo(this, 57, 89, textureX, textureY); // 152
		bodyModel[123] = new ModelRendererTurbo(this, 73, 89, textureX, textureY); // 153
		bodyModel[124] = new ModelRendererTurbo(this, 89, 89, textureX, textureY); // 154
		bodyModel[125] = new ModelRendererTurbo(this, 337, 81, textureX, textureY); // 155
		bodyModel[126] = new ModelRendererTurbo(this, 409, 81, textureX, textureY); // 156
		bodyModel[127] = new ModelRendererTurbo(this, 121, 89, textureX, textureY); // 157
		bodyModel[128] = new ModelRendererTurbo(this, 137, 89, textureX, textureY); // 158
		bodyModel[129] = new ModelRendererTurbo(this, 505, 81, textureX, textureY); // 159
		bodyModel[130] = new ModelRendererTurbo(this, 105, 89, textureX, textureY); // 160
		bodyModel[131] = new ModelRendererTurbo(this, 137, 89, textureX, textureY); // 161
		bodyModel[132] = new ModelRendererTurbo(this, 161, 89, textureX, textureY); // 162
		bodyModel[133] = new ModelRendererTurbo(this, 169, 89, textureX, textureY); // 163
		bodyModel[134] = new ModelRendererTurbo(this, 209, 89, textureX, textureY); // 167
		bodyModel[135] = new ModelRendererTurbo(this, 217, 89, textureX, textureY); // 168
		bodyModel[136] = new ModelRendererTurbo(this, 233, 89, textureX, textureY); // 169
		bodyModel[137] = new ModelRendererTurbo(this, 225, 89, textureX, textureY); // Box 169
		bodyModel[138] = new ModelRendererTurbo(this, 353, 89, textureX, textureY); // Box 171
		bodyModel[139] = new ModelRendererTurbo(this, 361, 89, textureX, textureY); // Box 171
		bodyModel[140] = new ModelRendererTurbo(this, 369, 89, textureX, textureY); // Box 173
		bodyModel[141] = new ModelRendererTurbo(this, 393, 89, textureX, textureY); // Box 173
		bodyModel[142] = new ModelRendererTurbo(this, 473, 89, textureX, textureY); // Box 179
		bodyModel[143] = new ModelRendererTurbo(this, 489, 89, textureX, textureY); // Box 179
		bodyModel[144] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 170
		bodyModel[145] = new ModelRendererTurbo(this, 49, 97, textureX, textureY); // Box 170
		bodyModel[146] = new ModelRendererTurbo(this, 153, 155, textureX, textureY); // Box 170
		bodyModel[147] = new ModelRendererTurbo(this, 449, 152, textureX, textureY); // Box 170
		bodyModel[148] = new ModelRendererTurbo(this, 33, 97, textureX, textureY); // Box 170
		bodyModel[149] = new ModelRendererTurbo(this, 321, 97, textureX, textureY); // Box 170
		bodyModel[150] = new ModelRendererTurbo(this, 369, 97, textureX, textureY); // Box 170
		bodyModel[151] = new ModelRendererTurbo(this, 424, 141, textureX, textureY); // Box 170
		bodyModel[152] = new ModelRendererTurbo(this, 201, 160, textureX, textureY); // Box 170
		bodyModel[153] = new ModelRendererTurbo(this, 185, 97, textureX, textureY); // Box 170
		bodyModel[154] = new ModelRendererTurbo(this, 353, 97, textureX, textureY); // Box 170
		bodyModel[155] = new ModelRendererTurbo(this, 489, 89, textureX, textureY); // Box 170
		bodyModel[156] = new ModelRendererTurbo(this, 377, 89, textureX, textureY); // Box 170
		bodyModel[157] = new ModelRendererTurbo(this, 505, 89, textureX, textureY); // Box 170
		bodyModel[158] = new ModelRendererTurbo(this, 401, 97, textureX, textureY); // Box 170
		bodyModel[159] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 170
		bodyModel[160] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 170
		bodyModel[161] = new ModelRendererTurbo(this, 17, 97, textureX, textureY); // Box 170
		bodyModel[162] = new ModelRendererTurbo(this, 81, 105, textureX, textureY); // Box 170
		bodyModel[163] = new ModelRendererTurbo(this, 113, 105, textureX, textureY); // Box 170
		bodyModel[164] = new ModelRendererTurbo(this, 33, 97, textureX, textureY); // Box 170
		bodyModel[165] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Box 170
		bodyModel[166] = new ModelRendererTurbo(this, 137, 105, textureX, textureY); // Box 170
		bodyModel[167] = new ModelRendererTurbo(this, 457, 113, textureX, textureY); // Box 170
		bodyModel[168] = new ModelRendererTurbo(this, 57, 97, textureX, textureY); // Box 170
		bodyModel[169] = new ModelRendererTurbo(this, 65, 97, textureX, textureY); // Box 170
		bodyModel[170] = new ModelRendererTurbo(this, 481, 113, textureX, textureY); // Box 170
		bodyModel[171] = new ModelRendererTurbo(this, 97, 121, textureX, textureY); // Box 170
		bodyModel[172] = new ModelRendererTurbo(this, 81, 97, textureX, textureY); // Box 170
		bodyModel[173] = new ModelRendererTurbo(this, 89, 97, textureX, textureY); // Box 170
		bodyModel[174] = new ModelRendererTurbo(this, 129, 121, textureX, textureY); // Box 170
		bodyModel[175] = new ModelRendererTurbo(this, 161, 121, textureX, textureY); // Box 170
		bodyModel[176] = new ModelRendererTurbo(this, 137, 97, textureX, textureY); // Box 170
		bodyModel[177] = new ModelRendererTurbo(this, 145, 97, textureX, textureY); // Box 170
		bodyModel[178] = new ModelRendererTurbo(this, 185, 121, textureX, textureY); // Box 170
		bodyModel[179] = new ModelRendererTurbo(this, 217, 121, textureX, textureY); // Box 170
		bodyModel[180] = new ModelRendererTurbo(this, 161, 97, textureX, textureY); // Box 170
		bodyModel[181] = new ModelRendererTurbo(this, 169, 97, textureX, textureY); // Box 170
		bodyModel[182] = new ModelRendererTurbo(this, 241, 121, textureX, textureY); // Box 170
		bodyModel[183] = new ModelRendererTurbo(this, 273, 121, textureX, textureY); // Box 170
		bodyModel[184] = new ModelRendererTurbo(this, 185, 97, textureX, textureY); // Box 170
		bodyModel[185] = new ModelRendererTurbo(this, 193, 97, textureX, textureY); // Box 170
		bodyModel[186] = new ModelRendererTurbo(this, 297, 121, textureX, textureY); // Box 170
		bodyModel[187] = new ModelRendererTurbo(this, 417, 121, textureX, textureY); // Box 170
		bodyModel[188] = new ModelRendererTurbo(this, 209, 97, textureX, textureY); // Box 170
		bodyModel[189] = new ModelRendererTurbo(this, 225, 97, textureX, textureY); // Box 170
		bodyModel[190] = new ModelRendererTurbo(this, 1, 129, textureX, textureY); // Box 170
		bodyModel[191] = new ModelRendererTurbo(this, 33, 129, textureX, textureY); // Box 170
		bodyModel[192] = new ModelRendererTurbo(this, 233, 97, textureX, textureY); // Box 170
		bodyModel[193] = new ModelRendererTurbo(this, 321, 97, textureX, textureY); // Box 170
		bodyModel[194] = new ModelRendererTurbo(this, 321, 129, textureX, textureY); // Box 170
		bodyModel[195] = new ModelRendererTurbo(this, 353, 129, textureX, textureY); // Box 170
		bodyModel[196] = new ModelRendererTurbo(this, 329, 97, textureX, textureY); // Box 170
		bodyModel[197] = new ModelRendererTurbo(this, 337, 97, textureX, textureY); // Box 170
		bodyModel[198] = new ModelRendererTurbo(this, 473, 129, textureX, textureY); // Box 170
		bodyModel[199] = new ModelRendererTurbo(this, 441, 129, textureX, textureY); // Box 170
		bodyModel[200] = new ModelRendererTurbo(this, 353, 97, textureX, textureY); // Box 170
		bodyModel[201] = new ModelRendererTurbo(this, 377, 97, textureX, textureY); // Box 170
		bodyModel[202] = new ModelRendererTurbo(this, 57, 137, textureX, textureY); // Box 170
		bodyModel[203] = new ModelRendererTurbo(this, 113, 137, textureX, textureY); // Box 170
		bodyModel[204] = new ModelRendererTurbo(this, 385, 97, textureX, textureY); // Box 170
		bodyModel[205] = new ModelRendererTurbo(this, 401, 97, textureX, textureY); // Box 170
		bodyModel[206] = new ModelRendererTurbo(this, 137, 137, textureX, textureY); // Box 170
		bodyModel[207] = new ModelRendererTurbo(this, 177, 137, textureX, textureY); // Box 170
		bodyModel[208] = new ModelRendererTurbo(this, 425, 97, textureX, textureY); // Box 170
		bodyModel[209] = new ModelRendererTurbo(this, 433, 97, textureX, textureY); // Box 170
		bodyModel[210] = new ModelRendererTurbo(this, 233, 137, textureX, textureY); // Box 170
		bodyModel[211] = new ModelRendererTurbo(this, 201, 137, textureX, textureY); // Box 170
		bodyModel[212] = new ModelRendererTurbo(this, 449, 97, textureX, textureY); // Box 170
		bodyModel[213] = new ModelRendererTurbo(this, 457, 97, textureX, textureY); // Box 170
		bodyModel[214] = new ModelRendererTurbo(this, 289, 137, textureX, textureY); // Box 170
		bodyModel[215] = new ModelRendererTurbo(this, 377, 137, textureX, textureY); // Box 170
		bodyModel[216] = new ModelRendererTurbo(this, 473, 97, textureX, textureY); // Box 170
		bodyModel[217] = new ModelRendererTurbo(this, 481, 97, textureX, textureY); // Box 170
		bodyModel[218] = new ModelRendererTurbo(this, 233, 159, textureX, textureY); // Box 170
		bodyModel[219] = new ModelRendererTurbo(this, 233, 169, textureX, textureY); // Box 170
		bodyModel[220] = new ModelRendererTurbo(this, 225, 179, textureX, textureY); // Box 170
		bodyModel[221] = new ModelRendererTurbo(this, 225, 188, textureX, textureY); // Box 170
		bodyModel[222] = new ModelRendererTurbo(this, 225, 179, textureX, textureY); // Box 170
		bodyModel[223] = new ModelRendererTurbo(this, 225, 188, textureX, textureY); // Box 170
		bodyModel[224] = new ModelRendererTurbo(this, 343, 181, textureX, textureY); // Box 170
		bodyModel[225] = new ModelRendererTurbo(this, 469, 152, textureX, textureY); // Box 170
		bodyModel[226] = new ModelRendererTurbo(this, 461, 162, textureX, textureY); // Box 170
		bodyModel[227] = new ModelRendererTurbo(this, 174, 157, textureX, textureY); // Box 170
		bodyModel[228] = new ModelRendererTurbo(this, 153, 169, textureX, textureY); // Box 170
		bodyModel[229] = new ModelRendererTurbo(this, 424, 159, textureX, textureY); // Box 170
		bodyModel[230] = new ModelRendererTurbo(this, 404, 152, textureX, textureY); // Box 170
		bodyModel[231] = new ModelRendererTurbo(this, 201, 175, textureX, textureY); // Box 170
		bodyModel[232] = new ModelRendererTurbo(this, 201, 193, textureX, textureY); // Box 170
		bodyModel[233] = new ModelRendererTurbo(this, 9, 178, textureX, textureY); // Box 170
		bodyModel[234] = new ModelRendererTurbo(this, 35, 157, textureX, textureY); // Box 170
		bodyModel[235] = new ModelRendererTurbo(this, 23, 189, textureX, textureY); // Box 170
		bodyModel[236] = new ModelRendererTurbo(this, 50, 159, textureX, textureY); // Box 170
		bodyModel[237] = new ModelRendererTurbo(this, 9, 199, textureX, textureY); // 02
		bodyModel[238] = new ModelRendererTurbo(this, 22, 200, textureX, textureY); // 02
		bodyModel[239] = new ModelRendererTurbo(this, 23, 179, textureX, textureY); // Box 170
		bodyModel[240] = new ModelRendererTurbo(this, 34, 179, textureX, textureY); // Box 170
		bodyModel[241] = new ModelRendererTurbo(this, 40, 200, textureX, textureY); // Box 170
		bodyModel[242] = new ModelRendererTurbo(this, 56, 200, textureX, textureY); // Box 170
		bodyModel[243] = new ModelRendererTurbo(this, 38, 212, textureX, textureY); // Box 170
		bodyModel[244] = new ModelRendererTurbo(this, 78, 178, textureX, textureY); // Box 170
		bodyModel[245] = new ModelRendererTurbo(this, 57, 212, textureX, textureY); // Box 170
		bodyModel[246] = new ModelRendererTurbo(this, 66, 212, textureX, textureY); // Box 170
		bodyModel[247] = new ModelRendererTurbo(this, 90, 165, textureX, textureY); // 06
		bodyModel[248] = new ModelRendererTurbo(this, 197, 278, textureX, textureY); // 35
		bodyModel[249] = new ModelRendererTurbo(this, 200, 289, textureX, textureY); // 36
		bodyModel[250] = new ModelRendererTurbo(this, 214, 291, textureX, textureY); // 39
		bodyModel[251] = new ModelRendererTurbo(this, 185, 299, textureX, textureY); // 41
		bodyModel[252] = new ModelRendererTurbo(this, 182, 293, textureX, textureY); // 42
		bodyModel[253] = new ModelRendererTurbo(this, 55, 137, textureX, textureY); // 45
		bodyModel[254] = new ModelRendererTurbo(this, 28, 134, textureX, textureY); // 46
		bodyModel[255] = new ModelRendererTurbo(this, 118, 181, textureX, textureY); // 48
		bodyModel[256] = new ModelRendererTurbo(this, 53, 329, textureX, textureY); // 39
		bodyModel[257] = new ModelRendererTurbo(this, 35, 134, textureX, textureY); // 46
		bodyModel[258] = new ModelRendererTurbo(this, 180, 279, textureX, textureY); // 35
		bodyModel[259] = new ModelRendererTurbo(this, 143, 309, textureX, textureY); // 35
		bodyModel[260] = new ModelRendererTurbo(this, 161, 270, textureX, textureY); // 35
		bodyModel[261] = new ModelRendererTurbo(this, 129, 197, textureX, textureY); // Box 170
		bodyModel[262] = new ModelRendererTurbo(this, 129, 197, textureX, textureY); // Box 170
		bodyModel[263] = new ModelRendererTurbo(this, 34, 255, textureX, textureY); // Box 170
		bodyModel[264] = new ModelRendererTurbo(this, 33, 254, textureX, textureY); // Box 170
		bodyModel[265] = new ModelRendererTurbo(this, 14, 251, textureX, textureY); // Box 170
		bodyModel[266] = new ModelRendererTurbo(this, 14, 251, textureX, textureY); // Box 170
		bodyModel[267] = new ModelRendererTurbo(this, 178, 349, textureX, textureY); // 35
		bodyModel[268] = new ModelRendererTurbo(this, 185, 299, textureX, textureY); // 41
		bodyModel[269] = new ModelRendererTurbo(this, 52, 253, textureX, textureY); // 39
		bodyModel[270] = new ModelRendererTurbo(this, 53, 318, textureX, textureY); // 39
		bodyModel[271] = new ModelRendererTurbo(this, 55, 350, textureX, textureY); // 36
		bodyModel[272] = new ModelRendererTurbo(this, 137, 329, textureX, textureY); // 36
		bodyModel[273] = new ModelRendererTurbo(this, 24, 425, textureX, textureY); // 01
		bodyModel[274] = new ModelRendererTurbo(this, 22, 286, textureX, textureY); // Box 170
		bodyModel[275] = new ModelRendererTurbo(this, 39, 286, textureX, textureY); // Box 170
		bodyModel[276] = new ModelRendererTurbo(this, 39, 295, textureX, textureY); // Box 170
		bodyModel[277] = new ModelRendererTurbo(this, 21, 295, textureX, textureY); // Box 170
		bodyModel[278] = new ModelRendererTurbo(this, 15, 210, textureX, textureY); // Box 170
		bodyModel[279] = new ModelRendererTurbo(this, 24, 425, textureX, textureY); // 01
		bodyModel[280] = new ModelRendererTurbo(this, 34, 274, textureX, textureY); // Box 170
		bodyModel[281] = new ModelRendererTurbo(this, 20, 265, textureX, textureY); // Box 170
		bodyModel[282] = new ModelRendererTurbo(this, 74, 265, textureX, textureY); // Box 170
		bodyModel[283] = new ModelRendererTurbo(this, 74, 287, textureX, textureY); // Box 170
		bodyModel[284] = new ModelRendererTurbo(this, 88, 291, textureX, textureY); // Box 170
		bodyModel[285] = new ModelRendererTurbo(this, 115, 262, textureX, textureY); // Box 170
		bodyModel[286] = new ModelRendererTurbo(this, 2, 258, textureX, textureY); // Box 170
		bodyModel[287] = new ModelRendererTurbo(this, 80, 344, textureX, textureY); // Box 170
		bodyModel[288] = new ModelRendererTurbo(this, 107, 288, textureX, textureY); // Box 170
		bodyModel[289] = new ModelRendererTurbo(this, 107, 316, textureX, textureY); // Box 170
		bodyModel[290] = new ModelRendererTurbo(this, 122, 305, textureX, textureY); // Box 170
		bodyModel[291] = new ModelRendererTurbo(this, 133, 305, textureX, textureY); // Box 170
		bodyModel[292] = new ModelRendererTurbo(this, 107, 305, textureX, textureY); // Box 170
		bodyModel[293] = new ModelRendererTurbo(this, 107, 316, textureX, textureY); // Box 170
		bodyModel[294] = new ModelRendererTurbo(this, 122, 305, textureX, textureY); // Box 170
		bodyModel[295] = new ModelRendererTurbo(this, 133, 305, textureX, textureY); // Box 170
		bodyModel[296] = new ModelRendererTurbo(this, 107, 305, textureX, textureY); // Box 170
		bodyModel[297] = new ModelRendererTurbo(this, 133, 291, textureX, textureY); // Box 14
		bodyModel[298] = new ModelRendererTurbo(this, 159, 297, textureX, textureY); // Box 14
		bodyModel[299] = new ModelRendererTurbo(this, 148, 297, textureX, textureY); // Box 14
		bodyModel[300] = new ModelRendererTurbo(this, 139, 297, textureX, textureY); // Box 14
		bodyModel[301] = new ModelRendererTurbo(this, 139, 291, textureX, textureY); // Box 14
		bodyModel[302] = new ModelRendererTurbo(this, 132, 297, textureX, textureY); // Box 14
		bodyModel[303] = new ModelRendererTurbo(this, 149, 291, textureX, textureY); // Box 14
		bodyModel[304] = new ModelRendererTurbo(this, 58, 293, textureX, textureY); // Box 170
		bodyModel[305] = new ModelRendererTurbo(this, 58, 281, textureX, textureY); // Box 170
		bodyModel[306] = new ModelRendererTurbo(this, 34, 317, textureX, textureY); // Box 170
		bodyModel[307] = new ModelRendererTurbo(this, 246, 204, textureX, textureY); // 105
		bodyModel[308] = new ModelRendererTurbo(this, 247, 201, textureX, textureY); // 105
		bodyModel[309] = new ModelRendererTurbo(this, 247, 198, textureX, textureY); // 105
		bodyModel[310] = new ModelRendererTurbo(this, 246, 204, textureX, textureY); // 105
		bodyModel[311] = new ModelRendererTurbo(this, 247, 201, textureX, textureY); // 105
		bodyModel[312] = new ModelRendererTurbo(this, 247, 198, textureX, textureY); // 105
		bodyModel[313] = new ModelRendererTurbo(this, 36, 286, textureX, textureY, "lamp"); // Lamp
		bodyModel[314] = new ModelRendererTurbo(this, 36, 281, textureX, textureY, "lamp"); // Lamp
		bodyModel[315] = new ModelRendererTurbo(this, 35, 256, textureX, textureY); // Box 368
		bodyModel[316] = new ModelRendererTurbo(this, 10, 146, textureX, textureY); // Box 369
		bodyModel[317] = new ModelRendererTurbo(this, 61, 177, textureX, textureY); // Box 370
		bodyModel[318] = new ModelRendererTurbo(this, 505, 17, textureX, textureY); // Box 371
		bodyModel[319] = new ModelRendererTurbo(this, 505, 1, textureX, textureY); // Box 372
		bodyModel[320] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // Box 373
		bodyModel[321] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Box 374
		bodyModel[322] = new ModelRendererTurbo(this, 385, 33, textureX, textureY); // Box 375
		bodyModel[323] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Box 376
		bodyModel[324] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // Box 377
		bodyModel[325] = new ModelRendererTurbo(this, 417, 1, textureX, textureY); // Box 378
		bodyModel[326] = new ModelRendererTurbo(this, 9, 1, textureX, textureY); // Box 379
		bodyModel[327] = new ModelRendererTurbo(this, 441, 1, textureX, textureY); // Box 380
		bodyModel[328] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // Box 381
		bodyModel[329] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // Box 382
		bodyModel[330] = new ModelRendererTurbo(this, 473, 1, textureX, textureY); // Box 383
		bodyModel[331] = new ModelRendererTurbo(this, 409, 1, textureX, textureY); // Box 384
		bodyModel[332] = new ModelRendererTurbo(this, 170, 228, textureX, textureY); // Box 385
		bodyModel[333] = new ModelRendererTurbo(this, 39, 227, textureX, textureY); // Box 386
		bodyModel[334] = new ModelRendererTurbo(this, 19, 228, textureX, textureY); // Box 387
		bodyModel[335] = new ModelRendererTurbo(this, 61, 177, textureX, textureY); // Box 388
		bodyModel[336] = new ModelRendererTurbo(this, 35, 256, textureX, textureY); // Box 389
		bodyModel[337] = new ModelRendererTurbo(this, 35, 256, textureX, textureY); // Box 391
		bodyModel[338] = new ModelRendererTurbo(this, 134, 218, textureX, textureY); // Box 392
		bodyModel[339] = new ModelRendererTurbo(this, 149, 235, textureX, textureY); // Box 394
		bodyModel[340] = new ModelRendererTurbo(this, 35, 256, textureX, textureY); // Box 396
		bodyModel[341] = new ModelRendererTurbo(this, 40, 200, textureX, textureY); // Box 397
		bodyModel[342] = new ModelRendererTurbo(this, 146, 207, textureX, textureY); // Box 399
		bodyModel[343] = new ModelRendererTurbo(this, 116, 246, textureX, textureY); // Box 401
		bodyModel[344] = new ModelRendererTurbo(this, 168, 184, textureX, textureY); // Box 402
		bodyModel[345] = new ModelRendererTurbo(this, 217, 89, textureX, textureY); // Box 403
		bodyModel[346] = new ModelRendererTurbo(this, 233, 89, textureX, textureY); // Box 404
		bodyModel[347] = new ModelRendererTurbo(this, 209, 89, textureX, textureY); // Box 405
		bodyModel[348] = new ModelRendererTurbo(this, 297, 73, textureX, textureY); // Box 406
		bodyModel[349] = new ModelRendererTurbo(this, 313, 73, textureX, textureY); // Box 407
		bodyModel[350] = new ModelRendererTurbo(this, 281, 73, textureX, textureY); // Box 408
		bodyModel[351] = new ModelRendererTurbo(this, 154, 222, textureX, textureY); // Box 415
		bodyModel[352] = new ModelRendererTurbo(this, 151, 200, textureX, textureY); // Box 416
		bodyModel[353] = new ModelRendererTurbo(this, 35, 256, textureX, textureY); // Box 417
		bodyModel[354] = new ModelRendererTurbo(this, 40, 200, textureX, textureY); // Box 418
		bodyModel[355] = new ModelRendererTurbo(this, 153, 184, textureX, textureY); // Box 419
		bodyModel[356] = new ModelRendererTurbo(this, 147, 245, textureX, textureY); // Box 420
		bodyModel[357] = new ModelRendererTurbo(this, 173, 201, textureX, textureY); // Box 421
		bodyModel[358] = new ModelRendererTurbo(this, 133, 234, textureX, textureY); // Box 422
		bodyModel[359] = new ModelRendererTurbo(this, 172, 213, textureX, textureY); // Box 423
		bodyModel[360] = new ModelRendererTurbo(this, 35, 256, textureX, textureY); // Box 424
		bodyModel[361] = new ModelRendererTurbo(this, 90, 175, textureX, textureY); // Box 425
		bodyModel[362] = new ModelRendererTurbo(this, 1, 218, textureX, textureY); // Box 170
		bodyModel[363] = new ModelRendererTurbo(this, 3, 235, textureX, textureY); // Box 385
		bodyModel[364] = new ModelRendererTurbo(this, 57, 240, textureX, textureY); // Box 386
		bodyModel[365] = new ModelRendererTurbo(this, 78, 242, textureX, textureY); // Box 170
		bodyModel[366] = new ModelRendererTurbo(this, 27, 219, textureX, textureY); // Box 385
		bodyModel[367] = new ModelRendererTurbo(this, 82, 211, textureX, textureY); // Box 170
		bodyModel[368] = new ModelRendererTurbo(this, 107, 207, textureX, textureY); // Box 170
		bodyModel[369] = new ModelRendererTurbo(this, 35, 240, textureX, textureY); // Box 386
		bodyModel[370] = new ModelRendererTurbo(this, 29, 373, textureX, textureY); // Box 170
		bodyModel[371] = new ModelRendererTurbo(this, 61, 385, textureX, textureY); // Box 385
		bodyModel[372] = new ModelRendererTurbo(this, 81, 402, textureX, textureY); // Box 385
		bodyModel[373] = new ModelRendererTurbo(this, 91, 399, textureX, textureY); // Box 385
		bodyModel[374] = new ModelRendererTurbo(this, 100, 370, textureX, textureY); // Box 385
		bodyModel[375] = new ModelRendererTurbo(this, 81, 402, textureX, textureY); // Box 385
		bodyModel[376] = new ModelRendererTurbo(this, 91, 399, textureX, textureY); // Box 385
		bodyModel[377] = new ModelRendererTurbo(this, 119, 370, textureX, textureY); // Box 385

		bodyModel[0].addShapeBox(0F, 0F, 0F, 89, 1, 20, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, 0.4F, 0F, -0.5F, 0.4F, 0F, -0.5F, 0.4F, 0F, -0.5F, 0.4F); // 01
		bodyModel[0].setRotationPoint(-45F, 0F, -10F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 89, 4, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F); // 02
		bodyModel[1].setRotationPoint(-45F, -3F, -11F);

		bodyModel[2].addShapeBox(0F, 0F, 0F, 89, 4, 1, 0F,0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F); // 03
		bodyModel[2].setRotationPoint(-45F, -3F, 10F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 89, 14, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, 0F, -0.9F); // 04
		bodyModel[3].setRotationPoint(-45F, -17F, -11F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 89, 14, 1, 0F,0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, 0F, 0F, 0F, 0F, 0F); // 05
		bodyModel[4].setRotationPoint(-45F, -17F, 10F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 89, 1, 1, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F); // 06
		bodyModel[5].setRotationPoint(-45F, -18F, -11F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 89, 1, 1, 0F,0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F); // 07
		bodyModel[6].setRotationPoint(-45F, -18F, 10F);

		bodyModel[7].addShapeBox(0F, 0F, -5F, 89, 2, 3, 0F,0F, 0F, -3F, 0F, 0F, -3F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, -3F, 0F, 0.1F, -3F); // 08
		bodyModel[7].setRotationPoint(-45F, -20F, -5F);

		bodyModel[8].addShapeBox(0F, 0F, -5F, 89, 2, 3, 0F,0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0.1F, -3F, 0F, 0.1F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // 09
		bodyModel[8].setRotationPoint(-45F, -20F, 12F);

		bodyModel[9].addShapeBox(0F, 0F, -5F, 85, 2, 7, 0F,0F, 0F, -6F, 0F, 0F, -6F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, -7F, 0F, 0.1F, -7F); // 10
		bodyModel[9].setRotationPoint(-41F, -22F, -2F);

		bodyModel[10].addShapeBox(0F, 0F, -5F, 85, 2, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -6F, 0F, 0F, -6F, 0F, 0.1F, -7F, 0F, 0.1F, -7F, 0F, 0F, 0F, 0F, 0F, 0F); // 11
		bodyModel[10].setRotationPoint(-41F, -22F, 5F);

		bodyModel[11].addShapeBox(0F, 0F, 0F, 1, 4, 22, 0F,0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -1F, 0F, -0.5F, -1F, 0F, -0.5F, -0.5F); // 12
		bodyModel[11].setRotationPoint(44F, -3F, -11F);

		bodyModel[12].addShapeBox(0F, 0F, 0F, 1, 14, 22, 0F,0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F); // 13
		bodyModel[12].setRotationPoint(44F, -17F, -11F);

		bodyModel[13].addShapeBox(0F, 0F, 0F, 1, 3, 4, 0F,0F, -2F, -1F, 0F, -3F, -0.5F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // 14
		bodyModel[13].setRotationPoint(44F, -20F, -11F);

		bodyModel[14].addShapeBox(0F, 0F, 0F, 1, 3, 4, 0F,0F, 0F, 0F, -1F, 0F, 0F, 0F, -3F, -0.5F, 0F, -2F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F); // 15
		bodyModel[14].setRotationPoint(44F, -20F, 7F);

		bodyModel[15].addShapeBox(0F, 0F, 0F, 1, 5, 6, 0F,0F, -2F, 0F, -1F, -2F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 16
		bodyModel[15].setRotationPoint(44F, -22F, -7F);

		bodyModel[16].addShapeBox(0F, 0F, 0F, 1, 5, 6, 0F,0F, 0F, 0F, -1F, 0F, 0F, -1F, -2F, 0F, 0F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 17
		bodyModel[16].setRotationPoint(44F, -22F, 1F);

		bodyModel[17].addShapeBox(0F, 0F, 0F, 1, 5, 2, 0F,0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 18
		bodyModel[17].setRotationPoint(44F, -22F, -1F);

		bodyModel[18].addShapeBox(0F, 0F, 0F, 2, 2, 20, 0F,-0.5F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -1F, -0.5F, 0F, -1F); // 19
		bodyModel[18].setRotationPoint(43F, 0F, -10F);

		bodyModel[19].addShapeBox(0F, 0F, 0F, 1, 18, 1, 0F,0.4F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0.4F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F); // 20
		bodyModel[19].setRotationPoint(45F, -18F, -5F);

		bodyModel[20].addShapeBox(0F, 0F, 0F, 1, 18, 1, 0F,0.4F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0.4F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F); // 21
		bodyModel[20].setRotationPoint(45F, -18F, 4F);

		bodyModel[21].addShapeBox(0F, 0F, 0F, 1, 2, 8, 0F,0.6F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.6F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F); // 22
		bodyModel[21].setRotationPoint(45F, -19F, -4F);

		bodyModel[22].addShapeBox(0F, 0F, 0F, 1, 18, 1, 0F,0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0F, 0F, 0F, 0F); // 23
		bodyModel[22].setRotationPoint(45F, -18F, -5F);

		bodyModel[23].addShapeBox(0F, 0F, 0F, 1, 18, 1, 0F,0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F); // 24
		bodyModel[23].setRotationPoint(45F, -18F, 4F);

		bodyModel[24].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F); // 25
		bodyModel[24].setRotationPoint(45F, 0F, -4F);

		bodyModel[25].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, -0.75F, 0F, -1F, -0.75F, 0F, 0F, 0F, 0F, 0F); // 26
		bodyModel[25].setRotationPoint(45F, 0F, -5F);

		bodyModel[26].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, -1F, 0F, 0F, -1F); // 27
		bodyModel[26].setRotationPoint(45F, 0F, 4F);

		bodyModel[27].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0.5F, 0F, -1F, 0.5F, 0F, 0F, 0F, 0F, 0F); // 28
		bodyModel[27].setRotationPoint(45F, 0F, -5F);

		bodyModel[28].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -1F, 0F, 0F, -1F); // 29
		bodyModel[28].setRotationPoint(45F, 0F, 4F);

		bodyModel[29].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.5F, 0F, -1F, -0.75F, 0F, -1F, -0.75F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0.5F, 0F, 0F); // 30
		bodyModel[29].setRotationPoint(45F, -19F, -5F);

		bodyModel[30].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.5F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, -1F, 0.5F, 0F, -1F, 0.5F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0.5F, 0F, 0F); // 31
		bodyModel[30].setRotationPoint(45F, -19F, 4F);

		bodyModel[31].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -1F, 0.5F, 0F, -1F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0F, 0F, 0F, 0F); // 32
		bodyModel[31].setRotationPoint(45F, -19F, -5F);

		bodyModel[32].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F); // 33
		bodyModel[32].setRotationPoint(45F, -19F, 4F);

		bodyModel[33].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.8F, 0.5F, 0F, -0.8F, 0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F); // 34
		bodyModel[33].setRotationPoint(44F, 1F, 0F);

		bodyModel[34].addShapeBox(0F, 0F, 0F, 0, 1, 1, 0F,0.1F, -0.4F, 0.7F, 0F, -0.4F, 0.7F, 0F, -0.4F, -0.3F, 0.1F, -0.4F, -0.3F, 0.1F, 0.8F, 0.7F, 0F, 0.8F, 0.7F, 0F, 0.8F, -0.3F, 0.1F, 0.8F, -0.3F); // 35
		bodyModel[34].setRotationPoint(47F, 1F, 0F);

		bodyModel[35].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.5F, 0.25F, -0.5F, -0.5F, 0.25F, -0.5F, -0.5F, -0.75F, 0F, -0.5F, -0.75F, 0F, 0F, 0.25F, -0.5F, 0F, 0.25F, -0.5F, 0F, -0.75F, 0F, 0F, -0.75F); // 36
		bodyModel[35].setRotationPoint(47F, 1F, 0F);

		bodyModel[36].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.2F, 0.25F, -0.5F, -0.2F, 0.25F, -0.5F, -0.2F, -0.75F, 0F, -0.2F, -0.75F, 0F, -0.3F, 0.25F, -0.5F, -0.3F, 0.25F, -0.5F, -0.3F, -0.75F, 0F, -0.3F, -0.75F); // 37
		bodyModel[36].setRotationPoint(47F, 2F, 0F);

		bodyModel[37].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // 64
		bodyModel[37].setRotationPoint(-41F, 2F, -6F);

		bodyModel[38].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // 65
		bodyModel[38].setRotationPoint(-41F, 2F, 6F);

		bodyModel[39].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // 66
		bodyModel[39].setRotationPoint(-27F, 2F, -6F);

		bodyModel[40].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // 67
		bodyModel[40].setRotationPoint(-27F, 2F, 6F);

		bodyModel[41].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,1F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0F, 0F, 1F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0.2F, 0F); // 68
		bodyModel[41].setRotationPoint(-41F, 2F, -8F);

		bodyModel[42].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,1F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0F, 0F, 1F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0.2F, 0F); // 69
		bodyModel[42].setRotationPoint(-41F, 2F, 7F);

		bodyModel[43].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0.2F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0.2F, 0F, 1F, 0.2F, 0F, 0F, 0.2F, 0F); // 70
		bodyModel[43].setRotationPoint(-27F, 2F, -8F);

		bodyModel[44].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0.2F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0.2F, 0F, 1F, 0.2F, 0F, 0F, 0.2F, 0F); // 71
		bodyModel[44].setRotationPoint(-27F, 2F, 7F);

		bodyModel[45].addShapeBox(0F, 0F, 0F, 9, 1, 1, 0F,0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F); // 72
		bodyModel[45].setRotationPoint(-36F, 2F, -8F);

		bodyModel[46].addShapeBox(0F, 0F, 0F, 9, 1, 1, 0F,0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F); // 73
		bodyModel[46].setRotationPoint(-36F, 2F, 7F);

		bodyModel[47].addShapeBox(0F, 0F, 0F, 1, 1, 16, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 74
		bodyModel[47].setRotationPoint(-39F, 4F, -8F);

		bodyModel[48].addShapeBox(0F, 0F, 0F, 1, 1, 16, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 75
		bodyModel[48].setRotationPoint(-25F, 4F, -8F);

		bodyModel[49].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F); // 76
		bodyModel[49].setRotationPoint(-42F, 3F, -8F);

		bodyModel[50].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F); // 77
		bodyModel[50].setRotationPoint(-42F, 3F, 7F);

		bodyModel[51].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F); // 78
		bodyModel[51].setRotationPoint(-22F, 3F, -8F);

		bodyModel[52].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F); // 79
		bodyModel[52].setRotationPoint(-22F, 3F, 7F);

		bodyModel[53].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F); // 80
		bodyModel[53].setRotationPoint(-42F, 5F, -8F);

		bodyModel[54].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F); // 81
		bodyModel[54].setRotationPoint(-42F, 5F, 7F);

		bodyModel[55].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F, 0F, -0.5F, -0.2F); // 82
		bodyModel[55].setRotationPoint(-25F, 5F, -8F);

		bodyModel[56].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F, 0F, -0.5F, -0.2F); // 83
		bodyModel[56].setRotationPoint(-25F, 5F, 7F);

		bodyModel[57].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F); // 84
		bodyModel[57].setRotationPoint(-41F, 3F, -8F);

		bodyModel[58].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F); // 85
		bodyModel[58].setRotationPoint(-24F, 3F, -8F);

		bodyModel[59].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, 0F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, 0.5F, -0.2F); // 86
		bodyModel[59].setRotationPoint(-38F, 4F, -8F);

		bodyModel[60].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.2F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F); // 87
		bodyModel[60].setRotationPoint(-28F, 4F, -8F);

		bodyModel[61].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, 0F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, 0.5F, -0.2F); // 88
		bodyModel[61].setRotationPoint(-38F, 4F, 7F);

		bodyModel[62].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.2F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F); // 89
		bodyModel[62].setRotationPoint(-28F, 4F, 7F);

		bodyModel[63].addShapeBox(0F, 0F, 0F, 1, 1, 14, 0F,0F, -0.5F, 0.2F, -0.5F, -0.5F, 0.2F, -0.5F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, 0.2F, -0.5F, 0F, 0.2F, -0.5F, 0F, 0.2F, 0F, 0F, 0.2F); // 90
		bodyModel[63].setRotationPoint(-42F, 4F, -7F);

		bodyModel[64].addShapeBox(0F, 0F, 0F, 1, 1, 14, 0F,-0.5F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, -0.5F, -0.5F, 0.2F, -0.5F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, -0.5F, 0F, 0.2F); // 91
		bodyModel[64].setRotationPoint(-22F, 4F, -7F);

		bodyModel[65].addBox(0F, 0F, 0F, 15, 1, 2, 0F); // 92
		bodyModel[65].setRotationPoint(-39F, 3F, -1F);

		bodyModel[66].addShapeBox(0F, 0F, 0F, 1, 3, 2, 0F,0.5F, -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // 93
		bodyModel[66].setRotationPoint(-32F, 0F, -1F);

		bodyModel[67].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F); // 94
		bodyModel[67].setRotationPoint(-36F, 3F, -8F);

		bodyModel[68].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F); // 95
		bodyModel[68].setRotationPoint(-29F, 3F, -8F);

		bodyModel[69].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F); // 96
		bodyModel[69].setRotationPoint(-36F, 3F, 7F);

		bodyModel[70].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F); // 97
		bodyModel[70].setRotationPoint(-29F, 3F, 7F);

		bodyModel[71].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,0.5F, 0.5F, -0.7F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0.5F, 0.5F, -0.7F, 0.5F, -0.5F, -0.7F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0.5F, -0.5F, -0.7F); // 98
		bodyModel[71].setRotationPoint(-33F, 4F, -8F);

		bodyModel[72].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.5F, 0.5F, 0F, 0.5F, 0.5F, -0.7F, 0.5F, 0.5F, -0.7F, -0.5F, 0.5F, 0F, -0.5F, -0.5F, 0F, 0.5F, -0.5F, -0.7F, 0.5F, -0.5F, -0.7F, -0.5F, -0.5F, 0F); // 99
		bodyModel[72].setRotationPoint(-32F, 4F, -8F);

		bodyModel[73].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,0.5F, 0.5F, -0.7F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0.5F, 0.5F, -0.7F, 0.5F, -0.5F, -0.7F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0.5F, -0.5F, -0.7F); // 100
		bodyModel[73].setRotationPoint(-33F, 4F, 6F);

		bodyModel[74].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.5F, 0.5F, 0F, 0.5F, 0.5F, -0.7F, 0.5F, 0.5F, -0.7F, -0.5F, 0.5F, 0F, -0.5F, -0.5F, 0F, 0.5F, -0.5F, -0.7F, 0.5F, -0.5F, -0.7F, -0.5F, -0.5F, 0F); // 101
		bodyModel[74].setRotationPoint(-32F, 4F, 6F);

		bodyModel[75].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // 102
		bodyModel[75].setRotationPoint(-32F, 3F, -8F);

		bodyModel[76].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F); // 103
		bodyModel[76].setRotationPoint(-32F, 3F, 7F);

		bodyModel[77].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0.2F, 0.5F, 0F, 0.2F, 0.5F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // 104
		bodyModel[77].setRotationPoint(-34F, 5F, -7F);

		bodyModel[78].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0.5F, 0F, 0.2F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F); // 105
		bodyModel[78].setRotationPoint(-34F, 5F, 6F);

		bodyModel[79].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F); // 106
		bodyModel[79].setRotationPoint(-41F, 3F, 7F);

		bodyModel[80].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F); // 107
		bodyModel[80].setRotationPoint(-24F, 3F, 7F);

		bodyModel[81].addBox(0F, 0F, 0F, 1, 1, 14, 0F); // 108
		bodyModel[81].setRotationPoint(-32F, 2F, -7F);

		bodyModel[82].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F); // 109
		bodyModel[82].setRotationPoint(-32F, 3F, -6F);

		bodyModel[83].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // 110
		bodyModel[83].setRotationPoint(-32F, 3F, 5F);

		bodyModel[84].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F); // 111
		bodyModel[84].setRotationPoint(-28F, 3F, -9F);

		bodyModel[85].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F,0F, -1.3F, -1F, 0.9F, -0.8F, -0.3F, 0.9F, -0.8F, -0.5F, 0F, -1.3F, 0.2F, 0F, -0.5F, -1F, 0.9F, -1F, -0.3F, 0.9F, -1F, -0.5F, 0F, -0.5F, 0.2F); // 112
		bodyModel[85].setRotationPoint(-31F, 3F, -9F);

		bodyModel[86].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.6F, 0F, -0.8F, -0.1F, 0F, -0.8F, -0.1F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.3F, -0.1F, 0F, -0.3F, -0.1F, 0F, -0.5F, -0.6F, 0F, -0.5F); // 113
		bodyModel[86].setRotationPoint(-28F, 3F, -9F);

		bodyModel[87].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // 117
		bodyModel[87].setRotationPoint(18F, 2F, -6F);

		bodyModel[88].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // 118
		bodyModel[88].setRotationPoint(18F, 2F, 6F);

		bodyModel[89].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // 119
		bodyModel[89].setRotationPoint(32F, 2F, -6F);

		bodyModel[90].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // 120
		bodyModel[90].setRotationPoint(32F, 2F, 6F);

		bodyModel[91].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,1F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0F, 0F, 1F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0.2F, 0F); // 121
		bodyModel[91].setRotationPoint(18F, 2F, -8F);

		bodyModel[92].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,1F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0F, 0F, 1F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0.2F, 0F); // 122
		bodyModel[92].setRotationPoint(18F, 2F, 7F);

		bodyModel[93].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0.2F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0.2F, 0F, 1F, 0.2F, 0F, 0F, 0.2F, 0F); // 123
		bodyModel[93].setRotationPoint(32F, 2F, -8F);

		bodyModel[94].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0.2F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 1F, 0.2F, 0F, 1F, 0.2F, 0F, 0F, 0.2F, 0F); // 124
		bodyModel[94].setRotationPoint(32F, 2F, 7F);

		bodyModel[95].addShapeBox(0F, 0F, 0F, 9, 1, 1, 0F,0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F); // 125
		bodyModel[95].setRotationPoint(23F, 2F, -8F);

		bodyModel[96].addShapeBox(0F, 0F, 0F, 9, 1, 1, 0F,0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F); // 126
		bodyModel[96].setRotationPoint(23F, 2F, 7F);

		bodyModel[97].addShapeBox(0F, 0F, 0F, 1, 1, 16, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 127
		bodyModel[97].setRotationPoint(20F, 4F, -8F);

		bodyModel[98].addShapeBox(0F, 0F, 0F, 1, 1, 16, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 128
		bodyModel[98].setRotationPoint(34F, 4F, -8F);

		bodyModel[99].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F); // 129
		bodyModel[99].setRotationPoint(17F, 3F, -8F);

		bodyModel[100].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F); // 130
		bodyModel[100].setRotationPoint(17F, 3F, 7F);

		bodyModel[101].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F); // 131
		bodyModel[101].setRotationPoint(37F, 3F, -8F);

		bodyModel[102].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F); // 132
		bodyModel[102].setRotationPoint(37F, 3F, 7F);

		bodyModel[103].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F); // 133
		bodyModel[103].setRotationPoint(17F, 5F, -8F);

		bodyModel[104].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -0.5F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F); // 134
		bodyModel[104].setRotationPoint(17F, 5F, 7F);

		bodyModel[105].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F, 0F, -0.5F, -0.2F); // 135
		bodyModel[105].setRotationPoint(34F, 5F, -8F);

		bodyModel[106].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F, -0.5F, -0.5F, -0.2F, 0F, -0.5F, -0.2F); // 136
		bodyModel[106].setRotationPoint(34F, 5F, 7F);

		bodyModel[107].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F); // 137
		bodyModel[107].setRotationPoint(18F, 3F, -8F);

		bodyModel[108].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F); // 138
		bodyModel[108].setRotationPoint(35F, 3F, -8F);

		bodyModel[109].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, 0F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, 0.5F, -0.2F); // 139
		bodyModel[109].setRotationPoint(21F, 4F, -8F);

		bodyModel[110].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.2F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F); // 140
		bodyModel[110].setRotationPoint(31F, 4F, -8F);

		bodyModel[111].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, 0F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, 0.5F, -0.2F); // 141
		bodyModel[111].setRotationPoint(21F, 4F, 7F);

		bodyModel[112].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.2F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F); // 142
		bodyModel[112].setRotationPoint(31F, 4F, 7F);

		bodyModel[113].addShapeBox(0F, 0F, 0F, 1, 1, 14, 0F,0F, -0.5F, 0.2F, -0.5F, -0.5F, 0.2F, -0.5F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, 0.2F, -0.5F, 0F, 0.2F, -0.5F, 0F, 0.2F, 0F, 0F, 0.2F); // 143
		bodyModel[113].setRotationPoint(17F, 4F, -7F);

		bodyModel[114].addShapeBox(0F, 0F, 0F, 1, 1, 14, 0F,-0.5F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, -0.5F, -0.5F, 0.2F, -0.5F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, -0.5F, 0F, 0.2F); // 144
		bodyModel[114].setRotationPoint(37F, 4F, -7F);

		bodyModel[115].addBox(0F, 0F, 0F, 15, 1, 2, 0F); // 145
		bodyModel[115].setRotationPoint(20F, 3F, -1F);

		bodyModel[116].addShapeBox(0F, 0F, 0F, 1, 3, 2, 0F,0.5F, -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // 146
		bodyModel[116].setRotationPoint(27F, 0F, -1F);

		bodyModel[117].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F); // 147
		bodyModel[117].setRotationPoint(22F, 3F, -8F);

		bodyModel[118].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F); // 148
		bodyModel[118].setRotationPoint(30F, 3F, -8F);

		bodyModel[119].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F); // 149
		bodyModel[119].setRotationPoint(23F, 3F, 7F);

		bodyModel[120].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F); // 150
		bodyModel[120].setRotationPoint(30F, 3F, 7F);

		bodyModel[121].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,0.5F, 0.5F, -0.7F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0.5F, 0.5F, -0.7F, 0.5F, -0.5F, -0.7F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0.5F, -0.5F, -0.7F); // 151
		bodyModel[121].setRotationPoint(26F, 4F, -8F);

		bodyModel[122].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.5F, 0.5F, 0F, 0.5F, 0.5F, -0.7F, 0.5F, 0.5F, -0.7F, -0.5F, 0.5F, 0F, -0.5F, -0.5F, 0F, 0.5F, -0.5F, -0.7F, 0.5F, -0.5F, -0.7F, -0.5F, -0.5F, 0F); // 152
		bodyModel[122].setRotationPoint(27F, 4F, -8F);

		bodyModel[123].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,0.5F, 0.5F, -0.7F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0.5F, 0.5F, -0.7F, 0.5F, -0.5F, -0.7F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0.5F, -0.5F, -0.7F); // 153
		bodyModel[123].setRotationPoint(26F, 4F, 6F);

		bodyModel[124].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.5F, 0.5F, 0F, 0.5F, 0.5F, -0.7F, 0.5F, 0.5F, -0.7F, -0.5F, 0.5F, 0F, -0.5F, -0.5F, 0F, 0.5F, -0.5F, -0.7F, 0.5F, -0.5F, -0.7F, -0.5F, -0.5F, 0F); // 154
		bodyModel[124].setRotationPoint(27F, 4F, 6F);

		bodyModel[125].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // 155
		bodyModel[125].setRotationPoint(27F, 3F, -8F);

		bodyModel[126].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.2F, 0F, -0.5F, -0.2F); // 156
		bodyModel[126].setRotationPoint(27F, 3F, 7F);

		bodyModel[127].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0.2F, 0.5F, 0F, 0.2F, 0.5F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // 157
		bodyModel[127].setRotationPoint(25F, 5F, -7F);

		bodyModel[128].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0.5F, 0F, 0.2F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F); // 158
		bodyModel[128].setRotationPoint(25F, 5F, 6F);

		bodyModel[129].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F); // 159
		bodyModel[129].setRotationPoint(18F, 3F, 7F);

		bodyModel[130].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, -0.5F, -0.2F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F); // 160
		bodyModel[130].setRotationPoint(35F, 3F, 7F);

		bodyModel[131].addBox(0F, 0F, 0F, 1, 1, 14, 0F); // 161
		bodyModel[131].setRotationPoint(27F, 2F, -7F);

		bodyModel[132].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F); // 162
		bodyModel[132].setRotationPoint(27F, 3F, -6F);

		bodyModel[133].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // 163
		bodyModel[133].setRotationPoint(27F, 3F, 5F);

		bodyModel[134].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, -0.5F, -0.5F, -0.5F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // 167
		bodyModel[134].setRotationPoint(23F, 3F, -9F);

		bodyModel[135].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F,0F, -0.8F, -0.3F, 0.9F, -1.3F, -1F, 0.9F, -1.3F, 0.2F, 0F, -0.8F, -0.5F, 0F, -1F, -0.3F, 0.9F, -0.5F, -1F, 0.9F, -0.5F, 0.2F, 0F, -1F, -0.5F); // 168
		bodyModel[135].setRotationPoint(23F, 3F, -9F);

		bodyModel[136].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.1F, 0F, -0.8F, -0.6F, 0F, -0.8F, -0.6F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -0.3F, -0.6F, 0F, -0.3F, -0.6F, 0F, -0.5F, -0.1F, 0F, -0.5F); // 169
		bodyModel[136].setRotationPoint(23F, 3F, -9F);

		bodyModel[137].addShapeBox(0F, 0F, 0F, 36, 5, 20, 0F,0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -1F, 0F, -2F, -1F, 0F, -2F, -1F, 0F, -2F, -1F, 0F, -2F); // Box 169
		bodyModel[137].setRotationPoint(-20F, 0F, -10F);

		bodyModel[138].addShapeBox(0F, 0F, 0F, 1, 5, 1, 0F,0F, -0.1F, 0F, -0.8F, -0.1F, 0F, -0.8F, -0.1F, -0.5F, 0F, -0.1F, -0.5F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.8F, 0F, 0F, -0.8F); // Box 171
		bodyModel[138].setRotationPoint(42F, 0F, 9F);

		bodyModel[139].addShapeBox(0F, 0F, 0F, 1, 5, 1, 0F,-0.8F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, -0.5F, -0.8F, -0.1F, -0.5F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, -0.8F, 0F, -0.8F); // Box 171
		bodyModel[139].setRotationPoint(39F, 0F, 9F);

		bodyModel[140].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, -0.6F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, -0.8F, 0F, -0.6F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F); // Box 173
		bodyModel[140].setRotationPoint(40F, 4F, 9F);

		bodyModel[141].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, -0.6F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, -0.7F, 0F, -0.6F, -0.7F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.7F, 0F, 0F, -0.7F); // Box 173
		bodyModel[141].setRotationPoint(40F, 2F, 9F);

		bodyModel[142].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F,0F, -0.5F, 0.5F, -0.5F, -0.5F, 0.5F, -0.5F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.2F, 0.5F, -0.5F, -0.2F, 0.5F, -0.5F, -0.2F, -0.5F, 0F, -0.2F, -0.5F); // Box 179
		bodyModel[142].setRotationPoint(41F, 1F, -8F);

		bodyModel[143].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0.7F, 0.2F, 0F, -1.3F, 0.2F, 0F, -1.3F, -1F, 0F, 0.7F, -1F, 0F, -2.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, -0.5F, -1F, 0F, -2.5F, -1F); // Box 179
		bodyModel[143].setRotationPoint(39F, 1F, -8F);

		bodyModel[144].addShapeBox(0F, 0F, 0F, 1, 3, 22, 0F,0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.5F, -0.8F, 0F, -0.5F, -0.8F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 170
		bodyModel[144].setRotationPoint(-41F, -3F, -11F);

		bodyModel[145].addShapeBox(0F, 0F, 0F, 1, 14, 22, 0F,0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 170
		bodyModel[145].setRotationPoint(-41F, -17F, -11F);

		bodyModel[146].addShapeBox(0F, 0F, 0F, 1, 1, 7, 0F,0F, 0F, -1.1F, -0.8F, 0F, -1.1F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[146].setRotationPoint(-41F, -18F, -11F);

		bodyModel[147].addShapeBox(0F, 0F, -5F, 1, 2, 6, 0F,0F, 0F, -3.1F, -0.8F, 0F, -3.1F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[147].setRotationPoint(-41F, -20F, -5F);

		bodyModel[148].addShapeBox(0F, 0F, -5F, 1, 2, 14, 0F,0F, -0.05F, -6.1F, -0.8F, -0.05F, -6.1F, -0.8F, -0.05F, -6.1F, 0F, -0.05F, -6.1F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 170
		bodyModel[148].setRotationPoint(-41F, -22F, -2F);

		bodyModel[149].addShapeBox(0F, 0F, 0F, 1, 3, 22, 0F,-0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.8F, 0F, -0.5F); // Box 170
		bodyModel[149].setRotationPoint(37F, -3F, -11F);

		bodyModel[150].addShapeBox(0F, 0F, 0F, 1, 14, 22, 0F,-0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F); // Box 170
		bodyModel[150].setRotationPoint(37F, -17F, -11F);

		bodyModel[151].addShapeBox(0F, 0F, 0F, 1, 1, 7, 0F,-0.8F, 0F, -1.1F, 0F, 0F, -1.1F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -0.8F, 0F, 0F); // Box 170
		bodyModel[151].setRotationPoint(37F, -18F, -11F);

		bodyModel[152].addShapeBox(0F, 0F, 0F, 1, 2, 6, 0F,-0.8F, 0F, -3.1F, 0F, 0F, -3.1F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -0.8F, 0F, 0F); // Box 170
		bodyModel[152].setRotationPoint(37F, -20F, -10F);

		bodyModel[153].addShapeBox(0F, 0F, 0F, 1, 2, 14, 0F,-0.8F, -0.05F, -6.1F, 0F, -0.05F, -6.1F, 0F, -0.05F, -6.1F, -0.8F, -0.05F, -6.1F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F); // Box 170
		bodyModel[153].setRotationPoint(37F, -22F, -7F);

		bodyModel[154].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[154].setRotationPoint(-38F, -4F, -11F);

		bodyModel[155].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[155].setRotationPoint(-34F, -11F, -11F);

		bodyModel[156].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[156].setRotationPoint(-36F, -3F, -9F);

		bodyModel[157].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[157].setRotationPoint(-36F, -3F, -6F);

		bodyModel[158].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[158].setRotationPoint(-38F, -4F, 3F);

		bodyModel[159].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.3F, -1F, 0F, -0.3F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[159].setRotationPoint(-34F, -11F, 3F);

		bodyModel[160].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[160].setRotationPoint(-36F, -3F, 5F);

		bodyModel[161].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[161].setRotationPoint(-36F, -3F, 8F);

		bodyModel[162].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[162].setRotationPoint(-29F, -4F, -11F);

		bodyModel[163].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[163].setRotationPoint(-25F, -11F, -11F);

		bodyModel[164].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[164].setRotationPoint(-27F, -3F, -9F);

		bodyModel[165].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[165].setRotationPoint(-27F, -3F, -6F);

		bodyModel[166].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[166].setRotationPoint(-29F, -4F, 3F);

		bodyModel[167].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.3F, -1F, 0F, -0.3F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[167].setRotationPoint(-25F, -11F, 3F);

		bodyModel[168].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[168].setRotationPoint(-27F, -3F, 5F);

		bodyModel[169].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[169].setRotationPoint(-27F, -3F, 8F);

		bodyModel[170].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[170].setRotationPoint(-20F, -4F, -11F);

		bodyModel[171].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[171].setRotationPoint(-16F, -11F, -11F);

		bodyModel[172].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[172].setRotationPoint(-18F, -3F, -9F);

		bodyModel[173].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[173].setRotationPoint(-18F, -3F, -6F);

		bodyModel[174].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[174].setRotationPoint(-20F, -4F, 3F);

		bodyModel[175].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.3F, -1F, 0F, -0.3F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[175].setRotationPoint(-16F, -11F, 3F);

		bodyModel[176].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[176].setRotationPoint(-18F, -3F, 5F);

		bodyModel[177].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[177].setRotationPoint(-18F, -3F, 8F);

		bodyModel[178].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[178].setRotationPoint(-11F, -4F, -11F);

		bodyModel[179].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[179].setRotationPoint(-7F, -11F, -11F);

		bodyModel[180].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[180].setRotationPoint(-9F, -3F, -9F);

		bodyModel[181].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[181].setRotationPoint(-9F, -3F, -6F);

		bodyModel[182].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[182].setRotationPoint(-11F, -4F, 3F);

		bodyModel[183].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.3F, -1F, 0F, -0.3F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[183].setRotationPoint(-7F, -11F, 3F);

		bodyModel[184].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[184].setRotationPoint(-9F, -3F, 5F);

		bodyModel[185].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[185].setRotationPoint(-9F, -3F, 8F);

		bodyModel[186].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[186].setRotationPoint(0F, -4F, -11F);

		bodyModel[187].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[187].setRotationPoint(4F, -11F, -11F);

		bodyModel[188].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[188].setRotationPoint(2F, -3F, -9F);

		bodyModel[189].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[189].setRotationPoint(2F, -3F, -6F);

		bodyModel[190].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[190].setRotationPoint(0F, -4F, 3F);

		bodyModel[191].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.3F, -1F, 0F, -0.3F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[191].setRotationPoint(4F, -11F, 3F);

		bodyModel[192].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[192].setRotationPoint(2F, -3F, 5F);

		bodyModel[193].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[193].setRotationPoint(2F, -3F, 8F);

		bodyModel[194].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[194].setRotationPoint(9F, -4F, -11F);

		bodyModel[195].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[195].setRotationPoint(13F, -11F, -11F);

		bodyModel[196].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[196].setRotationPoint(11F, -3F, -9F);

		bodyModel[197].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[197].setRotationPoint(11F, -3F, -6F);

		bodyModel[198].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[198].setRotationPoint(9F, -4F, 3F);

		bodyModel[199].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.3F, -1F, 0F, -0.3F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[199].setRotationPoint(13F, -11F, 3F);

		bodyModel[200].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[200].setRotationPoint(11F, -3F, 5F);

		bodyModel[201].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[201].setRotationPoint(11F, -3F, 8F);

		bodyModel[202].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[202].setRotationPoint(18F, -4F, -11F);

		bodyModel[203].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[203].setRotationPoint(22F, -11F, -11F);

		bodyModel[204].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[204].setRotationPoint(20F, -3F, -9F);

		bodyModel[205].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[205].setRotationPoint(20F, -3F, -6F);

		bodyModel[206].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[206].setRotationPoint(18F, -4F, 3F);

		bodyModel[207].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.3F, -1F, 0F, -0.3F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[207].setRotationPoint(22F, -11F, 3F);

		bodyModel[208].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[208].setRotationPoint(20F, -3F, 5F);

		bodyModel[209].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[209].setRotationPoint(20F, -3F, 8F);

		bodyModel[210].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[210].setRotationPoint(27F, -4F, -11F);

		bodyModel[211].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[211].setRotationPoint(31F, -11F, -11F);

		bodyModel[212].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[212].setRotationPoint(29F, -3F, -9F);

		bodyModel[213].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[213].setRotationPoint(29F, -3F, -6F);

		bodyModel[214].addShapeBox(0F, 0F, 0F, 4, 1, 8, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[214].setRotationPoint(27F, -4F, 3F);

		bodyModel[215].addShapeBox(0F, 0F, 0F, 1, 8, 8, 0F,-1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.3F, -1F, 0F, -0.3F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[215].setRotationPoint(31F, -11F, 3F);

		bodyModel[216].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[216].setRotationPoint(29F, -3F, 5F);

		bodyModel[217].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 170
		bodyModel[217].setRotationPoint(29F, -3F, 8F);

		bodyModel[218].addShapeBox(0F, 0F, 0F, 79, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.05F, 0F, 0F, -0.05F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, -0.05F, 0F, 0F, -0.05F); // Box 170
		bodyModel[218].setRotationPoint(-40.8F, -16F, 8F);

		bodyModel[219].addShapeBox(0F, 0F, 0F, 79, 1, 3, 0F,0F, 0F, -0.05F, 0F, 0F, -0.05F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.05F, 0F, 0F, -0.05F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 170
		bodyModel[219].setRotationPoint(-40.8F, -16F, -11F);

		bodyModel[220].addShapeBox(0F, 0F, 0F, 1, 3, 4, 0F,-0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, 0F, 0F, -0.8F, 0F, 0F); // Box 170
		bodyModel[220].setRotationPoint(-4F, -3F, -11F);

		bodyModel[221].addShapeBox(0F, 0F, 0F, 1, 14, 4, 0F,-0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 2.9F, 0F, -0.8F, 2.9F, 0F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -0.8F, 0F, 0F); // Box 170
		bodyModel[221].setRotationPoint(-4F, -17F, -11F);

		bodyModel[222].addShapeBox(0F, 0F, 0F, 1, 3, 4, 0F,-0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.6F, -0.8F, 0F, -0.6F); // Box 170
		bodyModel[222].setRotationPoint(-4F, -3F, 7F);

		bodyModel[223].addShapeBox(0F, 0F, 0F, 1, 14, 4, 0F,-0.8F, 2.9F, 0F, 0F, 2.9F, 0F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F); // Box 170
		bodyModel[223].setRotationPoint(-4F, -17F, 7F);

		bodyModel[224].addShapeBox(0F, 0F, -5F, 74, 1, 10, 0F,0F, -0.4F, 0F, 5F, -0.4F, 0F, 5F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[224].setRotationPoint(-41F, -21F, 0F);

		bodyModel[225].addShapeBox(0F, 0F, -5F, 1, 2, 3, 0F,0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 2.9F, 0F, 0F, 2.9F); // Box 170
		bodyModel[225].setRotationPoint(-41F, -20F, 9F);

		bodyModel[226].addShapeBox(0F, 0F, -5F, 1, 2, 8, 0F,0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[226].setRotationPoint(-41F, -20F, 1F);

		bodyModel[227].addShapeBox(0F, 0F, 0F, 1, 1, 7, 0F,0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -1.1F, 0F, 0F, -1.1F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 170
		bodyModel[227].setRotationPoint(-41F, -18F, 4F);

		bodyModel[228].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[228].setRotationPoint(-41F, -18F, -4F);

		bodyModel[229].addShapeBox(0F, 0F, 0F, 1, 1, 7, 0F,-0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.1F, -0.8F, 0F, -1.1F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F); // Box 170
		bodyModel[229].setRotationPoint(37F, -18F, 4F);

		bodyModel[230].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,-0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F); // Box 170
		bodyModel[230].setRotationPoint(37F, -18F, -4F);

		bodyModel[231].addShapeBox(0F, 0F, 0F, 1, 2, 8, 0F,-0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F); // Box 170
		bodyModel[231].setRotationPoint(37F, -20F, -4F);

		bodyModel[232].addShapeBox(0F, 0F, 0F, 1, 2, 6, 0F,-0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3.1F, -0.8F, 0F, -3.1F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F); // Box 170
		bodyModel[232].setRotationPoint(37F, -20F, 4F);

		bodyModel[233].addShapeBox(0F, 0F, 0F, 5, 10, 1, 0F,0F, 0F, 0F, -2.8F, 0F, 0F, -2.8F, 0F, -0.8F, 0F, 0F, -0.8F, -2F, 0F, 0F, -2.8F, 0F, 0F, -2.8F, 0F, -0.8F, -2F, 0F, -0.8F); // Box 170
		bodyModel[233].setRotationPoint(-47.2F, -17F, -11.01F);

		bodyModel[234].addShapeBox(0F, 0F, 0F, 5, 10, 1, 0F,0F, 0F, -0.8F, -2.8F, 0F, -0.8F, -2.8F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, -0.8F, -2.8F, 0F, -0.8F, -2.8F, 0F, 0F, -2F, 0F, 0F); // Box 170
		bodyModel[234].setRotationPoint(-47.2F, -17F, 10.01F);

		bodyModel[235].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, -1.8F, 0F, 0F, -1.8F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.7F, 0F, -1.2F, -0.7F, 0F, -1.2F, -0.7F, -0.88F, 0F, -0.7F, -0.8F); // Box 170
		bodyModel[235].setRotationPoint(-47.2F, -17F, -11F);

		bodyModel[236].addShapeBox(0F, 0F, 0F, 3, 5, 1, 0F,0F, 0F, -0.8F, -0.8F, 0F, -0.8F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, -0.8F, 0F, -0.8F, -0.8F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[236].setRotationPoint(-47.2F, -8F, 10F);

		bodyModel[237].addShapeBox(0F, 0F, 0F, 3, 4, 1, 0F,0F, 0F, -0.9F, -0.8F, 0F, -0.9F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.4F, -0.8F, -0.5F, -0.4F, -0.8F, -0.5F, -0.5F, 0F, -0.5F, -0.5F); // 02
		bodyModel[237].setRotationPoint(-47.2F, -3F, 10F);

		bodyModel[238].addShapeBox(0F, 0F, 0F, 2, 4, 1, 0F,0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, -0.9F, 0F, 0F, -0.8F, 0F, -0.5F, -0.5F, 0.2F, -0.5F, -0.5F, 0.2F, -0.5F, -0.4F, 0F, -0.5F, -0.4F); // 02
		bodyModel[238].setRotationPoint(-47.2F, -3F, -11F);

		bodyModel[239].addShapeBox(0F, 0F, 0F, 3, 5, 1, 0F,0F, 0F, 0F, -0.8F, 0F, 0F, -0.9F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, -0.8F, 0F, 0F, -0.9F, 0F, -0.8F, 0F, 0F, -0.8F); // Box 170
		bodyModel[239].setRotationPoint(-47.2F, -8F, -11F);

		bodyModel[240].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, -0.8F, -1.8F, 0F, -0.8F, -1.8F, 0F, 0F, 0F, 0F, 0F, 0F, -0.7F, -0.8F, -1.2F, -0.7F, -0.88F, -1.2F, -0.7F, 0F, 0F, -0.7F, 0F); // Box 170
		bodyModel[240].setRotationPoint(-47.2F, -17F, 10F);

		bodyModel[241].addShapeBox(0F, 0F, 0F, 1, 2, 5, 0F,0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -0.7F, -0.3F, -0.8F, -0.7F, -0.3F, -0.8F, -0.7F, -0.1F, 0F, -0.7F, -0.1F); // Box 170
		bodyModel[241].setRotationPoint(-49.4F, -17F, -9F);

		bodyModel[242].addShapeBox(0F, 0F, 0F, 1, 2, 5, 0F,0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -0.7F, -0.3F, -0.8F, -0.7F, -0.3F, -0.8F, -0.7F, -0.1F, 0F, -0.7F, -0.1F); // Box 170
		bodyModel[242].setRotationPoint(-49.4F, -17F, 3.8F);

		bodyModel[243].addShapeBox(0F, 0F, 0F, 1, 3, 5, 0F,0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0.1F, 0.5F, -0.5F, -0.8F, 0.5F, -0.5F, -0.8F, 0.5F, -0.1F, 0F, 0.5F, -0.1F); // Box 170
		bodyModel[243].setRotationPoint(-49.4F, -3F, -9F);
		bodyModel[243].rotateAngleZ = 0.08726646F;

		bodyModel[244].addShapeBox(0F, 0F, 0F, 1, 19, 10, 0F,0F, 0F, -0.6F, -0.8F, 0F, -0.6F, -0.8F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.9F, -0.6F, -0.8F, -0.9F, -0.6F, -0.8F, -0.9F, -0.6F, 0F, -0.9F, -0.6F); // Box 170
		bodyModel[244].setRotationPoint(-51.2F, -18F, -5F);

		bodyModel[245].addShapeBox(0F, 0F, 0F, 1, 7, 2, 0F,0F, 0.7F, -0.3F, -0.8F, 0.7F, -0.3F, -0.8F, 0.7F, -0.1F, 0F, 0.7F, -0.1F, 0F, 0F, -0.6F, -0.8F, 0F, -0.6F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 170
		bodyModel[245].setRotationPoint(-49.4F, -15F, -6F);

		bodyModel[246].addShapeBox(0F, 0F, 0F, 1, 7, 2, 0F,0F, 0.7F, -0.1F, -0.8F, 0.7F, -0.1F, -0.8F, 0.7F, -0.3F, 0F, 0.7F, -0.3F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.6F, 0F, 0F, -0.6F); // Box 170
		bodyModel[246].setRotationPoint(-49.4F, -15F, 4F);

		bodyModel[247].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.4F, 0F, -1F, 0F, 0F, -1F, 0F, -0.1F, 0F, -0.4F, -0.1F, 0F, 0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0.2F, 0F, -0.8F); // 06
		bodyModel[247].setRotationPoint(-47F, -18F, -11F);

		bodyModel[248].addShapeBox(0F, 0F, 0F, 1, 1, 4, 0F,0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.3F, 0F, 0F, 0.3F, 0F, 0F, 0.3F, 0F, 0F, 0.3F, 0F); // 35
		bodyModel[248].setRotationPoint(-50F, 0F, -2F);

		bodyModel[249].addShapeBox(0F, 0F, 0F, 2, 1, 4, 0F,0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // 36
		bodyModel[249].setRotationPoint(-50F, 2F, -2F);

		bodyModel[250].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // 39
		bodyModel[250].setRotationPoint(-51.5F, 1F, 7F);

		bodyModel[251].addShapeBox(0F, 0F, 0F, 1, 3, 3, 0F,-0.8F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.4F, -0.8F, -0.5F, 0.4F, -0.8F, -0.5F, -0.6F, 0F, -0.5F, -0.6F, 0F, -0.5F, 0.4F, -0.8F, -0.5F, 0.4F); // 41
		bodyModel[251].setRotationPoint(-52.5F, 0F, -9F);

		bodyModel[252].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F, -0.5F, 0F, -0.3F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // 42
		bodyModel[252].setRotationPoint(-51.5F, 1F, 0F);

		bodyModel[253].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, -0.5F, 0.25F, 0F, -0.5F, 0.25F, 0F, -0.5F, -0.75F, -0.5F, -0.5F, -0.75F, -0.5F, 0.5F, 0.25F, 0F, 0.5F, 0.25F, 0F, 0.5F, -0.75F, -0.5F, 0.5F, -0.75F); // 45
		bodyModel[253].setRotationPoint(-51.5F, 1F, 0F);

		bodyModel[254].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.5F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F, -0.5F, 0.25F, 0F, -0.5F, 0.25F, 0F, -0.5F, -0.75F, -0.5F, -0.5F, -0.75F); // 46
		bodyModel[254].setRotationPoint(-52.5F, 1.75F, 0F);

		bodyModel[255].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-1F, 0.1F, 0.5F, 0.1F, 0.1F, 0.5F, 0.1F, 0.1F, -0.5F, -1F, 0.1F, -0.5F, -1F, 0.3F, 0.5F, 0.1F, 0.3F, 0.5F, 0.1F, 0.3F, -0.5F, -1F, 0.3F, -0.5F); // 48
		bodyModel[255].setRotationPoint(-52.5F, 1F, 0F);

		bodyModel[256].addShapeBox(0F, 0F, 0F, 7, 3, 5, 0F,-2F, -0.2F, 0.5F, -4.3F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, -5F, -0.2F, -0.2F, 0F, 0.3F, 0.5F, -4.5F, 0.3F, -0.2F, -1F, 0.3F, -1.2F, 0F, 0.3F, -1.7F); // 39
		bodyModel[256].setRotationPoint(-50F, 2.3F, 4.9F);

		bodyModel[257].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.5F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F, -0.5F, 0.25F, 0F, -0.5F, 0.25F, 0F, -0.5F, -0.75F, -0.5F, -0.5F, -0.75F); // 46
		bodyModel[257].setRotationPoint(-52.5F, 1F, 0F);

		bodyModel[258].addShapeBox(0F, 0F, 0F, 2, 2, 5, 0F,0F, -0.5F, 0.5F, 6F, -0.5F, 0.5F, 4F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0.55F, -0.3F, 5F, 0.55F, -0.3F, 0F, 0.55F, 0.5F, 0F, 0.55F, 0.5F); // 35
		bodyModel[258].setRotationPoint(-50F, 0F, -10F);

		bodyModel[259].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, -0.5F, 0F, 4F, -0.5F, 0F, 4F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0.55F, 0F, 0F, 0.55F, 0F, 0F, 0.55F, 0.5F, 0F, 0.55F, 0.5F); // 35
		bodyModel[259].setRotationPoint(-50F, 0F, 2F);

		bodyModel[260].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, -0.5F, 0.5F, 4F, -0.5F, 0.5F, 4F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.55F, 0.5F, 0F, 0.55F, 0.5F, 0F, 0.55F, 0F, 0F, 0.55F, 0F); // 35
		bodyModel[260].setRotationPoint(-50F, 0F, -4F);

		bodyModel[261].addShapeBox(0F, 0F, 0F, 3, 10, 1, 0F,-0.1F, -1.3F, 0F, -2.45F, -1.3F, 0F, -2.45F, -1.3F, -0.82F, -0.1F, -1.3F, -0.8F, -1.8F, -1F, 0F, -1.1F, -1F, 0F, -1.1F, -1F, -0.8F, -1.8F, -1F, -0.8F); // Box 170
		bodyModel[261].setRotationPoint(-47.3F, -17F, -11F);

		bodyModel[262].addShapeBox(0F, 0F, 0F, 3, 10, 1, 0F,-0.1F, -1.3F, -0.8F, -2.65F, -1.3F, -0.88F, -2.65F, -1.3F, 0F, -0.1F, -1.3F, 0F, -1.8F, -1F, -0.8F, -1.1F, -1F, -0.8F, -1.1F, -1F, 0F, -1.8F, -1F, 0F); // Box 170
		bodyModel[262].setRotationPoint(-47.3F, -17F, 10F);

		bodyModel[263].addShapeBox(0F, 0F, 0F, 1, 1, 4, 0F,0F, -0.7F, -0.3F, -0.8F, -0.7F, -0.3F, -0.8F, -0.7F, -0.4F, 0F, -0.7F, -0.4F, 0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.4F, 0F, 0F, -0.4F); // Box 170
		bodyModel[263].setRotationPoint(-49.4F, -9F, -9F);

		bodyModel[264].addShapeBox(0F, 0F, 0F, 1, 1, 5, 0F,0F, -0.7F, -0.1F, -0.8F, -0.7F, -0.1F, -0.8F, -0.7F, -0.3F, 0F, -0.7F, -0.3F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 170
		bodyModel[264].setRotationPoint(-49.4F, -9F, 4F);

		bodyModel[265].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.7F, -0.8F, -1.3F, -0.7F, -0.8F, -1.3F, -0.7F, 0F, 0F, -0.7F, 0F, 0F, 0F, -0.8F, -1.2F, 0F, -0.8F, -1.2F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[265].setRotationPoint(-47.2F, -9F, 10F);

		bodyModel[266].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.7F, 0F, -1.3F, -0.7F, 0F, -1.3F, -0.7F, -0.8F, 0F, -0.7F, -0.8F, 0F, 0F, 0F, -1.2F, 0F, 0F, -1.2F, 0F, -0.8F, 0F, 0F, -0.8F); // Box 170
		bodyModel[266].setRotationPoint(-47.2F, -9F, -11F);

		bodyModel[267].addShapeBox(0F, 0F, 0F, 2, 2, 5, 0F,0F, -0.5F, 0.5F, 4F, -0.5F, 0.5F, 6F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0.55F, 0.5F, 0F, 0.55F, 0.5F, 5F, 0.55F, -0.3F, 0F, 0.55F, -0.3F); // 35
		bodyModel[267].setRotationPoint(-50F, 0F, 5F);

		bodyModel[268].addShapeBox(0F, 0F, 0F, 1, 3, 3, 0F,-0.8F, -0.5F, 0.4F, 0F, -0.5F, 0.4F, 0F, -0.5F, 0.2F, -0.8F, -0.5F, 0.2F, -0.8F, -0.5F, 0.4F, 0F, -0.5F, 0.4F, 0F, -0.5F, -0.6F, -0.8F, -0.5F, -0.6F); // 41
		bodyModel[268].setRotationPoint(-52.5F, 0F, 6F);

		bodyModel[269].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // 39
		bodyModel[269].setRotationPoint(-51.5F, 1F, -8F);

		bodyModel[270].addShapeBox(0F, 0F, 0F, 7, 3, 5, 0F,-5F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, -4.3F, -0.2F, -0.2F, -2F, -0.2F, 0.5F, 0F, 0.3F, -1.7F, -1F, 0.3F, -1.2F, -4.5F, 0.3F, -0.2F, 0F, 0.3F, 0.5F); // 39
		bodyModel[270].setRotationPoint(-50F, 2.3F, -9.9F);

		bodyModel[271].addShapeBox(0F, 0F, 0F, 3, 2, 9, 0F,-1F, -0.1F, 0F, -1.9F, -0.1F, 0F, -1.9F, -0.1F, -0.2F, -1F, -0.1F, -0.2F, 0F, -0.4F, 0F, -1.2F, -0.6F, 0F, -1.2F, -0.6F, -0.2F, 0F, -0.4F, -0.2F); // 36
		bodyModel[271].setRotationPoint(-50F, 4F, -4.4F);

		bodyModel[272].addShapeBox(0F, 0F, 0F, 0, 2, 9, 0F,-1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, -0.2F, -1F, 0F, -0.2F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, -0.2F, 0F, -0.3F, -0.2F); // 36
		bodyModel[272].setRotationPoint(-49F, 2.5F, -4.4F);

		bodyModel[273].addShapeBox(0F, 0F, 0F, 2, 1, 20, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, 0.4F, 0F, -0.5F, 0.4F, 0F, -0.5F, 0.4F, 0F, -0.5F, 0.4F); // 01
		bodyModel[273].setRotationPoint(-47F, 0F, -10F);

		bodyModel[274].addShapeBox(0F, 0F, 0F, 2, 2, 6, 0F,0F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, -0.2F, 0F, 0.1F, -2.3F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.2F, 0F, 0F, -2.3F); // Box 170
		bodyModel[274].setRotationPoint(-49.2F, -9.3F, 5F);

		bodyModel[275].addShapeBox(0F, 0F, 0F, 2, 2, 6, 0F,0F, 0.1F, -2.3F, 0.1F, 0.1F, -0.2F, 0.1F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, -2.3F, 0.1F, 0F, -0.2F, 0.1F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[275].setRotationPoint(-49.2F, -9.3F, -11F);

		bodyModel[276].addShapeBox(0F, 0F, 0F, 2, 1, 6, 0F,0F, -0.8F, -2.3F, 0.1F, -0.8F, -0.2F, 0.1F, -0.8F, 0.5F, 0F, -0.8F, 0.5F, 0F, 0F, -2.3F, 0.1F, 0F, -0.2F, 0.1F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 170
		bodyModel[276].setRotationPoint(-49.2F, -10.4F, -11F);

		bodyModel[277].addShapeBox(0F, 0F, 0F, 2, 1, 6, 0F,0F, -0.8F, 0F, 0.1F, -0.8F, 0F, 0.1F, -0.8F, -0.2F, 0F, -0.8F, -2.3F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.2F, 0F, 0F, -2.3F); // Box 170
		bodyModel[277].setRotationPoint(-49.2F, -10.4F, 5F);

		bodyModel[278].addShapeBox(0F, 0F, 0F, 1, 5, 5, 0F,0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 170
		bodyModel[278].setRotationPoint(-49.4F, -8F, -9F);

		bodyModel[279].addShapeBox(0F, 0F, 0F, 2, 1, 20, 0F,0F, 0F, -1.3F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -1.3F, 0F, -0.5F, -1.4F, 0F, -0.5F, 0.4F, 0F, -0.5F, 0.4F, 0F, -0.5F, -1.4F); // 01
		bodyModel[279].setRotationPoint(-49F, 0F, -10F);

		bodyModel[280].addShapeBox(0F, 0F, 0F, 1, 7, 34, 0F,0F, 0F, -0.2F, -1F, 0F, -0.2F, -1F, 0F, -27.5F, 0F, 0F, -27.5F, 0F, -5.4F, -0.2F, -0.5F, -5.4F, -0.2F, -0.5F, -5.4F, -27.5F, 0F, -5.4F, -27.5F); // Box 170
		bodyModel[280].setRotationPoint(-47.1F, -9.6F, -11F);

		bodyModel[281].addShapeBox(0F, 0F, 0F, 9, 1, 13, 0F,0F, 0F, -0.5F, -7F, 0F, -0.5F, -7F, 0F, -9.5F, 0F, 0F, -9.5F, 0F, 0F, -0.5F, -7F, -0.6F, -0.5F, -7F, -0.6F, -9.5F, 0F, 0F, -9.5F); // Box 170
		bodyModel[281].setRotationPoint(-47.1F, -8F, -10F);

		bodyModel[282].addShapeBox(0F, 0F, 0F, 13, 12, 5, 0F,0F, 0F, -0.1F, -10.3F, 0F, -0.1F, -10.3F, 0F, -3.5F, 0F, 0F, -3.5F, 0F, -4F, -0.5F, -10.3F, -4F, -0.5F, -10.3F, -4F, -3.5F, 0F, -4F, -3.5F); // Box 170
		bodyModel[282].setRotationPoint(-47.1F, -8F, -11F);

		bodyModel[283].addShapeBox(0F, 0F, 0F, 1, 8, 4, 0F,-0.5F, -0.7F, 0F, 0F, -0.7F, 0F, 0F, -0.7F, 0F, -0.5F, -0.7F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 170
		bodyModel[283].setRotationPoint(-48.1F, -8F, -10F);

		bodyModel[284].addShapeBox(0F, 0F, 0F, 3, 2, 4, 0F,0F, -0.7F, -0.5F, -0.4F, -2F, -0.5F, -0.4F, -2F, -0.5F, 0F, -0.7F, -0.5F, 0F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 170
		bodyModel[284].setRotationPoint(-47.1F, -2F, -10F);

		bodyModel[285].addShapeBox(0F, 0F, 0F, 7, 12, 8, 0F,0F, 0F, -0.5F, -4.5F, 0F, -0.5F, -4.5F, 0F, -5.5F, 0F, 0F, -5.5F, 0F, -4F, -0.5F, -4.5F, -4F, -0.5F, -4.5F, -4F, -5.5F, 0F, -4F, -5.5F); // Box 170
		bodyModel[285].setRotationPoint(-47.1F, -8F, -7F);

		bodyModel[286].addShapeBox(0F, 0F, 0F, 1, 3, 13, 0F,0F, -0.3F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -9.7F, 0F, -0.3F, -9.7F, 0F, -2.2F, 0F, 0F, -2.2F, 0F, 0F, -2.2F, -9.7F, 0F, -2.2F, -9.7F); // Box 170
		bodyModel[286].setRotationPoint(-48.1F, -10.3F, -9F);

		bodyModel[287].addShapeBox(0F, 0F, 0F, 40, 11, 1, 0F,0F, 0F, 0.3F, -37F, 0F, -0.3F, -37F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -10F, 0.3F, -37F, -10F, -0.3F, -37F, -10F, -0.1F, 0F, -10F, -0.1F); // Box 170
		bodyModel[287].setRotationPoint(-48.1F, -11.5F, -5F);

		bodyModel[288].addShapeBox(0F, 0F, 0F, 4, 8, 6, 0F,-0.7F, -0.7F, 0F, -0.5F, -0.7F, 0F, -0.5F, -0.7F, -0.2F, -1.5F, -0.7F, -0.5F, -0.7F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.5F, -0.7F, 0F, -2F); // Box 170
		bodyModel[288].setRotationPoint(-49.1F, -8F, 5F);

		bodyModel[289].addShapeBox(0F, 0F, 0F, 3, 1, 3, 0F,-0.5F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 170
		bodyModel[289].setRotationPoint(-45F, -5F, 6F);

		bodyModel[290].addShapeBox(0F, 0F, 0F, 1, 5, 3, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[290].setRotationPoint(-42F, -9F, 6F);

		bodyModel[291].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,-0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F); // Box 170
		bodyModel[291].setRotationPoint(-42F, -11F, 6F);

		bodyModel[292].addShapeBox(0F, 0F, 0F, 3, 4, 3, 0F,-0.7F, 0F, -0.2F, -0.1F, 0F, -0.2F, -0.1F, 0F, -0.2F, -0.7F, 0F, -0.2F, -0.7F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.7F, 0F, -0.2F); // Box 170
		bodyModel[292].setRotationPoint(-44.7F, -4F, 6F);

		bodyModel[293].addShapeBox(0F, 0F, 0F, 3, 1, 3, 0F,-1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F); // Box 170
		bodyModel[293].setRotationPoint(-45.1F, -5F, -9.5F);

		bodyModel[294].addShapeBox(0F, 0F, 0F, 1, 5, 3, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 170
		bodyModel[294].setRotationPoint(-42.1F, -9F, -9.5F);

		bodyModel[295].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,-0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F); // Box 170
		bodyModel[295].setRotationPoint(-42.1F, -11F, -9.5F);

		bodyModel[296].addShapeBox(0F, 0F, 0F, 3, 4, 3, 0F,-1F, 0F, -0.2F, -0.1F, 0F, -0.2F, -0.1F, 0F, -0.2F, -1F, 0F, -0.2F, -1F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -1F, 0F, -0.2F); // Box 170
		bodyModel[296].setRotationPoint(-44.6F, -4F, -9.5F);

		bodyModel[297].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.2F, -0.5F, -0.2F, -0.2F, -0.5F, -0.2F, -0.2F, -0.5F, -0.2F, -0.2F, -0.5F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F); // Box 14
		bodyModel[297].setRotationPoint(-46F, -9F, -10.7F);

		bodyModel[298].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.2F, -0.2F, -0.2F, -0.2F, -0.2F, -0.2F, -0.2F, -0.2F, -0.2F, -0.2F, -0.2F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F); // Box 14
		bodyModel[298].setRotationPoint(-46F, -9F, -6F);

		bodyModel[299].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.6F, -0.3F, -0.65F, -0.25F, -0.3F, -1.5F, -0.3F, -0.3F, -0.3F, -0.5F, -0.3F, -1.2F, -0.6F, -0.5F, -0.65F, -0.25F, -0.5F, -1.5F, -0.3F, -0.5F, -0.3F, -0.5F, -0.5F, -1.2F); // Box 14
		bodyModel[299].setRotationPoint(-47F, -9F, -7F);

		bodyModel[300].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.55F, -0.2F, -0.5F, -1.15F, -0.2F, -0.7F, -1.45F, -0.2F, -1.05F, -0.35F, -0.2F, -1.25F, -0.55F, -0.4F, -0.5F, -1.15F, -0.4F, -0.7F, -1.45F, -0.4F, -1.05F, -0.35F, -0.4F, -1.25F); // Box 14
		bodyModel[300].setRotationPoint(-47F, -9F, -7F);

		bodyModel[301].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.9F, -0.3F, -1.2F, -0.4F, -0.6F, -0.4F, -0.3F, -0.6F, -1.4F, -1F, -0.3F, -0.65F, -0.9F, -0.5F, -1.2F, -0.4F, -0.2F, -0.4F, -0.3F, -0.2F, -1.4F, -1F, -0.5F, -0.65F); // Box 14
		bodyModel[301].setRotationPoint(-47.2F, -9F, -10.5F);

		bodyModel[302].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.4F, -0.4F, -0.4F, -0.4F, -0.4F, -0.4F, -0.4F, -0.4F, -0.4F, -0.4F, -0.4F, -0.4F, -0.4F, 0F, -0.4F, -0.4F, 0F, -0.4F, -0.4F, 0F, -0.4F, -0.4F, 0F, -0.4F); // Box 14
		bodyModel[302].setRotationPoint(-46.5F, -9F, -8.4F);

		bodyModel[303].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.3F, -0.8F, -0.3F, -0.3F, -0.8F, -0.3F, -0.3F, -0.8F, -0.3F, -0.3F, -0.8F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F); // Box 14
		bodyModel[303].setRotationPoint(-46.6F, -9F, -9.4F);

		bodyModel[304].addShapeBox(0F, 0F, 0F, 2, 9, 1, 0F,-1.6F, -0.5F, -0.6F, 0F, -0.1F, -0.6F, 0F, 0F, 0F, -1.6F, -0.1F, 0F, -0.65F, -0.6F, -0.65F, -1F, -0.6F, -0.6F, -1F, -0.6F, 0F, -0.6F, -0.6F, 0F); // Box 170
		bodyModel[304].setRotationPoint(-49.2F, -18F, -10F);

		bodyModel[305].addShapeBox(0F, 0F, 0F, 2, 9, 1, 0F,-1.6F, -0.1F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.6F, -1.6F, -0.5F, -0.6F, -0.6F, -0.6F, 0F, -1F, -0.6F, 0F, -1F, -0.6F, -0.6F, -0.65F, -0.6F, -0.65F); // Box 170
		bodyModel[305].setRotationPoint(-49.2F, -18F, 9F);

		bodyModel[306].addShapeBox(0F, 0F, 0F, 1, 5, 6, 0F,0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, -2.7F, 0F, -0.5F, -2.7F, 0F, -0.5F, -2.7F, -0.2F, 0F, -2.7F, -0.2F); // Box 170
		bodyModel[306].setRotationPoint(-47.1F, -9.6F, 5F);

		bodyModel[307].addShapeBox(0F, 0F, 0F, 14, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 105
		bodyModel[307].setRotationPoint(-38.5F, 4.75F, 8F);

		bodyModel[308].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, -0.375F, 0F, 0F, -0.375F, 0F, 0F, -0.375F, 0F, 0F, -0.375F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 105
		bodyModel[308].setRotationPoint(-32.5F, 5.75F, 8F);

		bodyModel[309].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, -0.625F, -0.3F, 0F, -0.625F, -0.3F, 0F, -0.625F, -0.3F, 0F, -0.625F, -0.3F); // 105
		bodyModel[309].setRotationPoint(-31.5F, 5.75F, 8F);

		bodyModel[310].addShapeBox(0F, 0F, 0F, 14, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 105
		bodyModel[310].setRotationPoint(-38.5F, 4.75F, -9F);

		bodyModel[311].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, -0.375F, 0F, 0F, -0.375F, 0F, 0F, -0.375F, 0F, 0F, -0.375F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // 105
		bodyModel[311].setRotationPoint(-32.5F, 5.75F, -9F);

		bodyModel[312].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, -0.625F, -0.3F, 0F, -0.625F, -0.3F, 0F, -0.625F, -0.3F, 0F, -0.625F, -0.3F); // 105
		bodyModel[312].setRotationPoint(-31.5F, 5.75F, -9F);

		bodyModel[313].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.4F, 0F, 0.1F, 0.4F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.4F, 0F, 0F, 0.4F); // Lamp
		bodyModel[313].setRotationPoint(-49.3F, -9.3F, -8F);

		bodyModel[314].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0.1F, 0.4F, 0F, 0.1F, 0.4F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Lamp
		bodyModel[314].setRotationPoint(-49.3F, -9.3F, 6F);

		bodyModel[315].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,-2.2F, -0.7F, 0F, 1.2F, -0.7F, -0.2F, -0.8F, -0.7F, -0.7F, 0F, -0.7F, -0.7F, -2.2F, 0F, 0F, 1.2F, 0F, -0.2F, -0.8F, 0F, -0.7F, 0F, 0F, -0.7F); // Box 368
		bodyModel[315].setRotationPoint(-49.4F, -9F, -11F);

		bodyModel[316].addShapeBox(0F, 0F, 0F, 1, 5, 3, 0F,-2.2F, 0F, 0F, 1.2F, 0F, -0.2F, 0.2F, 0F, -0.7F, 0F, 0F, -0.7F, -2.2F, 0F, 0F, 1.1F, 0F, -0.2F, 0.2F, 0F, -0.7F, 0F, 0F, -0.7F); // Box 369
		bodyModel[316].setRotationPoint(-49.4F, -8F, -11F);

		bodyModel[317].addShapeBox(0F, 0F, 0F, 1, 3, 3, 0F,-2.2F, -0.2F, 0F, 1.2F, -0.2F, -0.2F, 0.2F, 0F, -0.7F, 0F, 0F, -0.7F, -1.9F, 0.7F, -0.5F, 0.9F, 0.6F, -0.7F, 0.2F, 0.5F, -0.5F, 0.1F, 0.5F, -0.5F); // Box 370
		bodyModel[317].setRotationPoint(-49.4F, -3F, -11F);
		bodyModel[317].rotateAngleZ = 0.08726646F;

		bodyModel[318].addShapeBox(0F, 0F, 0F, 1, 18, 1, 0F,0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F); // Box 371
		bodyModel[318].setRotationPoint(-51F, -18F, 4F);

		bodyModel[319].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0.5F, 0F, -1F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F); // Box 372
		bodyModel[319].setRotationPoint(-51F, -19F, 4F);

		bodyModel[320].addShapeBox(0F, 0F, 0F, 3, 2, 8, 0F,0.5F, 0F, 0F, 0.6F, 0F, 0F, 0.6F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F); // Box 373
		bodyModel[320].setRotationPoint(-51F, -19F, -4F);

		bodyModel[321].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.5F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0.5F, 0F, 0F); // Box 374
		bodyModel[321].setRotationPoint(-51F, -19F, -5F);

		bodyModel[322].addShapeBox(0F, 0F, 0F, 1, 18, 1, 0F,0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0.5F, 0F, 0F); // Box 375
		bodyModel[322].setRotationPoint(-51F, -18F, -5F);

		bodyModel[323].addShapeBox(0F, 0F, 0F, 2, 1, 8, 0F,0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F); // Box 376
		bodyModel[323].setRotationPoint(-51F, 0F, -4F);

		bodyModel[324].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0.5F, 0F, 0F); // Box 377
		bodyModel[324].setRotationPoint(-51F, 0F, -5F);

		bodyModel[325].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -0.5F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0.5F, 0F, -1F); // Box 378
		bodyModel[325].setRotationPoint(-51F, 0F, 4F);

		bodyModel[326].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, -0.75F, 0F, -1F); // Box 379
		bodyModel[326].setRotationPoint(-51F, 0F, 4F);

		bodyModel[327].addShapeBox(0F, 0F, 0F, 2, 18, 1, 0F,-0.75F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F); // Box 380
		bodyModel[327].setRotationPoint(-51F, -18F, 4F);

		bodyModel[328].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,-0.75F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -1F, -0.75F, 0F, -1F, -0.75F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.75F, 0F, 0F); // Box 381
		bodyModel[328].setRotationPoint(-51F, -19F, 4F);

		bodyModel[329].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,-0.75F, 0F, -1F, 0.5F, 0F, -1F, 0.5F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.75F, 0F, 0F); // Box 382
		bodyModel[329].setRotationPoint(-51F, -19F, -5F);

		bodyModel[330].addShapeBox(0F, 0F, 0F, 5, 18, 1, 0F,-0.75F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F); // Box 383
		bodyModel[330].setRotationPoint(-51F, -18F, -5F);

		bodyModel[331].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, -0.75F, 0F, 0F); // Box 384
		bodyModel[331].setRotationPoint(-51F, 0F, -5F);

		bodyModel[332].addShapeBox(0F, 0F, 0F, 1, 5, 5, 0F,0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 385
		bodyModel[332].setRotationPoint(-49.4F, -8F, 4F);

		bodyModel[333].addShapeBox(0F, 0F, 0F, 1, 3, 5, 0F,0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0.5F, -0.1F, -0.8F, 0.5F, -0.1F, -0.8F, 0.5F, -0.5F, 0.1F, 0.5F, -0.5F); // Box 386
		bodyModel[333].setRotationPoint(-49.4F, -3F, 4F);
		bodyModel[333].rotateAngleZ = 0.08726646F;

		bodyModel[334].addShapeBox(0F, 0F, 0F, 1, 5, 3, 0F,0F, 0F, -0.7F, 0.2F, 0F, -0.7F, 1.2F, 0F, -0.2F, -2.2F, 0F, 0F, 0F, 0F, -0.7F, 0.2F, 0F, -0.7F, 1.1F, 0F, -0.2F, -2.2F, 0F, 0F); // Box 387
		bodyModel[334].setRotationPoint(-49.4F, -8F, 8F);

		bodyModel[335].addShapeBox(0F, 0F, 0F, 1, 3, 3, 0F,0F, 0F, -0.7F, 0.2F, 0F, -0.7F, 1.2F, -0.2F, -0.2F, -2.2F, -0.2F, 0F, 0.1F, 0.5F, -0.5F, 0.2F, 0.5F, -0.5F, 0.9F, 0.6F, -0.7F, -1.9F, 0.7F, -0.5F); // Box 388
		bodyModel[335].setRotationPoint(-49.4F, -3F, 8F);
		bodyModel[335].rotateAngleZ = 0.08726646F;

		bodyModel[336].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,-2.2F, 0F, 0F, 1.2F, 0F, -0.2F, -0.8F, 0F, -0.7F, 0F, 0F, -0.7F, -2.2F, 0.25F, 0F, 1.2F, 0.25F, -0.2F, -0.8F, 0.25F, -0.7F, 0F, 0.25F, -0.7F); // Box 389
		bodyModel[336].setRotationPoint(-49.4F, -17F, -11F);

		bodyModel[337].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0F, -0.7F, -0.8F, 0F, -0.7F, 1.2F, 0F, -0.2F, -2.2F, 0F, 0F, 0F, 0.25F, -0.7F, -0.8F, 0.25F, -0.7F, 1.2F, 0.25F, -0.2F, -2.2F, 0.25F, 0F); // Box 391
		bodyModel[337].setRotationPoint(-49.4F, -17F, 8F);

		bodyModel[338].addShapeBox(0F, 0F, -5F, 2, 2, 3, 0F,-1.95F, 0F, -3F, 0F, 0F, -3F, 0F, -0.1F, 0F, -1.95F, -0.1F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, -3F, -0.4F, 0.1F, -3F); // Box 392
		bodyModel[338].setRotationPoint(-47F, -20F, -5F);

		bodyModel[339].addShapeBox(0F, 0F, -5F, 2, 2, 3, 0F,-1.95F, 0F, -3.05F, -0.05F, 0F, -3F, -0.05F, -0.1F, 0F, -1.95F, -0.1F, 0.05F, 1.75F, 0F, -2F, -1.6F, 0F, 0F, -1.6F, 0.1F, -3F, 1.6F, 0F, -1F); // Box 394
		bodyModel[339].setRotationPoint(-47F, -20F, -5F);

		bodyModel[340].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,-2.8F, 0F, -1F, 1.85F, 0F, -1.1F, -0.2F, 0F, 0F, -0.65F, 0F, 0F, -2.2F, 0F, 0F, 1.2F, 0F, -0.2F, -0.8F, 0F, -0.7F, 0F, 0F, -0.7F); // Box 396
		bodyModel[340].setRotationPoint(-49.4F, -18F, -11F);

		bodyModel[341].addShapeBox(0F, 0F, 0F, 1, 1, 5, 0F,-0.65F, 0F, -1F, -0.2F, 0F, -1F, 0.3F, 0.65F, -0.1F, -1.3F, 0.65F, -0.1F, 0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 397
		bodyModel[341].setRotationPoint(-49.4F, -18F, -9F);

		bodyModel[342].addShapeBox(0F, 0F, 0F, 1, 2, 5, 0F,-0.7F, 0F, -0.05F, -0.3F, -0.1F, -0.05F, -0.25F, 0.45F, 0.25F, -0.75F, 0.5F, 0.25F, 3F, 0F, 1F, -3.8F, 0F, 1F, -2.75F, -1F, 0F, 2F, -1F, 0F); // Box 399
		bodyModel[342].setRotationPoint(-45.75F, -20F, -7F);

		bodyModel[343].addShapeBox(0F, 0F, -5F, 4, 2, 7, 0F,-3.95F, 0F, -6F, 0F, 0F, -6F, 0F, 0F, 0F, -3.95F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, -7F, 0F, 0.1F, -7F); // Box 401
		bodyModel[343].setRotationPoint(-45F, -22F, -2F);

		bodyModel[344].addShapeBox(0F, 0F, 0F, 4, 1, 6, 0F,0.05F, -2F, 0F, -4F, -2F, 0F, -0.05F, 0F, 0F, 0F, -1.5F, -0.75F, 0.05F, 1.1F, -0.05F, -4F, 1.1F, 0F, 0F, -0.9F, 0F, 0F, 0.55F, -0.75F); // Box 402
		bodyModel[344].setRotationPoint(-45F, -22F, -7F);

		bodyModel[345].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F,0.9F, -1.3F, 0.2F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.3F, 0.9F, -1.3F, -1F, 0.9F, -0.5F, 0.2F, 0F, -1F, -0.5F, 0F, -1F, -0.3F, 0.9F, -0.5F, -1F); // Box 403
		bodyModel[345].setRotationPoint(-26F, 3F, 8F);

		bodyModel[346].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.6F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -0.8F, -0.6F, 0F, -0.8F, -0.6F, 0F, -0.5F, -0.1F, 0F, -0.5F, -0.1F, 0F, -0.3F, -0.6F, 0F, -0.3F); // Box 404
		bodyModel[346].setRotationPoint(-24F, 3F, 8F);

		bodyModel[347].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.5F, -0.5F, -0.5F, -0.5F); // Box 405
		bodyModel[347].setRotationPoint(-24F, 3F, 8F);

		bodyModel[348].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F,0.9F, -0.8F, -0.5F, 0F, -1.3F, 0.2F, 0F, -1.3F, -1F, 0.9F, -0.8F, -0.3F, 0.9F, -1F, -0.5F, 0F, -0.5F, 0.2F, 0F, -0.5F, -1F, 0.9F, -1F, -0.3F); // Box 406
		bodyModel[348].setRotationPoint(24F, 3F, 8F);

		bodyModel[349].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.1F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.8F, -0.1F, 0F, -0.8F, -0.1F, 0F, -0.5F, -0.6F, 0F, -0.5F, -0.6F, 0F, -0.3F, -0.1F, 0F, -0.3F); // Box 407
		bodyModel[349].setRotationPoint(23F, 3F, 8F);

		bodyModel[350].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, -0.5F, 0F, -0.5F, -0.5F); // Box 408
		bodyModel[350].setRotationPoint(23F, 3F, 8F);

		bodyModel[351].addShapeBox(0F, 0F, 0F, 4, 1, 2, 0F,0F, -1.5F, 0.75F, -0.05F, 0F, 0F, -0.05F, 0F, 0F, 0F, -1.5F, 0.75F, 0F, 0.55F, 0.75F, -0.05F, -0.9F, 0F, -0.05F, -0.9F, 0F, 0F, 0.55F, 0.75F); // Box 415
		bodyModel[351].setRotationPoint(-45F, -22F, -1F);

		bodyModel[352].addShapeBox(0F, 0F, 0F, 3, 1, 3, 0F,-0.25F, -1.5F, 0.5F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, -0.25F, -1.5F, 0.5F, -0.55F, 0.5F, 0.5F, 0F, -0.95F, 0.25F, 0F, -0.95F, 0.25F, -0.55F, 0.5F, 0.5F); // Box 416
		bodyModel[352].setRotationPoint(-48F, -20.5F, -1.5F);

		bodyModel[353].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, -0.7F, -0.7F, -0.8F, -0.7F, -0.7F, 1.2F, -0.7F, -0.2F, -2.2F, -0.7F, 0F, 0F, 0F, -0.7F, -0.8F, 0F, -0.7F, 1.2F, 0F, -0.2F, -2.2F, 0F, 0F); // Box 417
		bodyModel[353].setRotationPoint(-49.4F, -9F, 8F);

		bodyModel[354].addShapeBox(0F, 0F, 0F, 1, 1, 5, 0F,-1.3F, 0.65F, -0.1F, 0.3F, 0.65F, -0.1F, -0.2F, 0F, -1F, -0.65F, 0F, -1F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 418
		bodyModel[354].setRotationPoint(-49.4F, -18F, 4F);

		bodyModel[355].addShapeBox(0F, 0F, 0F, 1, 2, 5, 0F,-0.75F, 0.5F, 0.25F, -0.25F, 0.45F, 0.25F, -0.3F, -0.1F, -0.05F, -0.7F, 0F, -0.05F, 2F, -1F, 0F, -2.75F, -1F, 0F, -3.8F, 0F, 1F, 3F, 0F, 1F); // Box 419
		bodyModel[355].setRotationPoint(-45.75F, -20F, 2F);

		bodyModel[356].addShapeBox(0F, 0F, -5F, 4, 2, 7, 0F,-3.95F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -6F, -3.95F, 0F, -6F, 0F, 0.1F, -7F, 0F, 0.1F, -7F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 420
		bodyModel[356].setRotationPoint(-45F, -22F, 5F);

		bodyModel[357].addShapeBox(0F, 0F, 0F, 4, 1, 6, 0F,0F, -1.5F, -0.75F, -0.05F, 0F, 0F, -4F, -2F, 0F, 0.05F, -2F, 0F, 0F, 0.55F, -0.75F, 0F, -0.9F, 0F, -4F, 1.1F, 0F, 0.05F, 1.1F, -0.05F); // Box 421
		bodyModel[357].setRotationPoint(-45F, -22F, 1F);

		bodyModel[358].addShapeBox(0F, 0F, -5F, 2, 2, 3, 0F,-1.95F, -0.1F, 0.05F, -0.05F, -0.1F, 0F, -0.05F, 0F, -3F, -1.95F, 0F, -3.05F, 1.6F, 0F, -1F, -1.6F, 0.1F, -3F, -1.6F, 0F, 0F, 1.75F, 0F, -2F); // Box 422
		bodyModel[358].setRotationPoint(-47F, -20F, 12F);

		bodyModel[359].addShapeBox(0F, 0F, -5F, 2, 2, 3, 0F,-1.95F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -3F, -1.95F, 0F, -3F, -0.4F, 0.1F, -3F, 0F, 0.1F, -3F, 0F, 0F, 0F, -0.4F, 0F, 0F); // Box 423
		bodyModel[359].setRotationPoint(-47F, -20F, 12F);

		bodyModel[360].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,-0.65F, 0F, 0F, -0.2F, 0F, 0F, 1.85F, 0F, -1.1F, -2.8F, 0F, -1F, 0F, 0F, -0.7F, -0.8F, 0F, -0.7F, 1.2F, 0F, -0.2F, -2.2F, 0F, 0F); // Box 424
		bodyModel[360].setRotationPoint(-49.4F, -18F, 8F);

		bodyModel[361].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.4F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -1F, -0.4F, 0F, -1F, 0.2F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, 0F, 0.2F, 0F, 0F); // Box 425
		bodyModel[361].setRotationPoint(-47F, -18F, 10F);

		bodyModel[362].addShapeBox(0F, 0F, 0F, 1, 5, 5, 0F,0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -4F, -0.3F, 0.2F, -4F, -0.3F, 0.2F, -4F, -0.1F, 0F, -4F, -0.1F); // Box 170
		bodyModel[362].setRotationPoint(-49.4F, -8F, -9F);

		bodyModel[363].addShapeBox(0F, 0F, 0F, 1, 5, 5, 0F,0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, -4F, -0.1F, 0.2F, -4F, -0.1F, 0.2F, -4F, -0.3F, 0F, -4F, -0.3F); // Box 385
		bodyModel[363].setRotationPoint(-49.4F, -8F, 4F);

		bodyModel[364].addShapeBox(0F, 0F, 0F, 1, 3, 5, 0F,0F, -2F, -0.1F, 0.2F, -2F, -0.1F, 0.2F, -2F, -0.3F, 0F, -2F, -0.3F, 0F, 0.5F, -0.1F, -0.8F, 0.5F, -0.1F, -0.8F, 0.5F, -0.5F, 0.1F, 0.5F, -0.5F); // Box 386
		bodyModel[364].setRotationPoint(-49.4F, -3F, 4F);
		bodyModel[364].rotateAngleZ = 0.08726646F;

		bodyModel[365].addShapeBox(0F, 0F, 0F, 1, 3, 5, 0F,0F, -2F, -0.3F, 0.2F, -2F, -0.3F, 0.2F, -2F, -0.1F, 0.1F, -2F, -0.1F, 0F, 0.5F, -0.5F, -0.8F, 0.5F, -0.5F, -0.8F, 0.5F, -0.1F, 0F, 0.5F, -0.1F); // Box 170
		bodyModel[365].setRotationPoint(-49.4F, -3F, -9F);
		bodyModel[365].rotateAngleZ = 0.08726646F;

		bodyModel[366].addShapeBox(0F, 0F, 0F, 1, 5, 5, 0F,0F, -1F, -0.1F, -0.8F, -1F, -0.1F, 0.2F, -1F, -3.5F, 0F, -1F, -3.5F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, 0.2F, 0F, -3.5F, 0F, 0F, -3.5F); // Box 385
		bodyModel[366].setRotationPoint(-49.4F, -8F, 4F);

		bodyModel[367].addShapeBox(0F, 0F, 0F, 1, 5, 5, 0F,0F, -1F, -3.5F, 0.2F, -1F, -3.5F, -0.8F, -1F, -0.1F, 0F, -1F, -0.1F, 0F, 0F, -3.5F, 0.2F, 0F, -3.5F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 170
		bodyModel[367].setRotationPoint(-49.4F, -8F, -9F);

		bodyModel[368].addShapeBox(0F, 0F, 0F, 1, 3, 5, 0F,0F, 0F, -3.5F, 0.2F, 0F, -3.5F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -1F, -3.5F, 0.2F, -1F, -3.5F, -0.8F, -1F, -0.1F, 0F, -1F, -0.1F); // Box 170
		bodyModel[368].setRotationPoint(-49.4F, -3F, -9F);
		bodyModel[368].rotateAngleZ = 0.08726646F;

		bodyModel[369].addShapeBox(0F, 0F, 0F, 1, 3, 5, 0F,0F, 0F, -0.1F, -0.8F, 0F, -0.1F, 0.2F, 0F, -3.5F, 0F, 0F, -3.5F, -0.1F, -1F, -0.1F, -0.8F, -1F, -0.1F, 0.2F, -1F, -3.5F, 0F, -1F, -3.5F); // Box 386
		bodyModel[369].setRotationPoint(-49.4F, -3F, 4F);
		bodyModel[369].rotateAngleZ = 0.08726646F;

		bodyModel[370].addShapeBox(4F, 0F, 0F, 1, 8, 4, 0F,0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.3F, -0.8F, 0F, -0.3F, -0.8F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 170
		bodyModel[370].setRotationPoint(-52.5F, -8F, -9F);

		bodyModel[371].addShapeBox(0F, 0F, 0F, 1, 8, 4, 0F,0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.1F, -0.8F, 0F, -0.1F, -0.8F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 385
		bodyModel[371].setRotationPoint(-48.5F, -8F, 5F);

		bodyModel[372].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 385
		bodyModel[372].setRotationPoint(-49.5F, -6.75F, 7.5F);

		bodyModel[373].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 385
		bodyModel[373].setRotationPoint(-49.5F, -6.75F, 5.8F);

		bodyModel[374].addShapeBox(0F, 0F, 0F, 1, 8, 4, 0F,-0.2F, -1.2F, -0.3F, -0.8F, -1.2F, -0.3F, -0.8F, -1.2F, -1F, -0.2F, -1.2F, -1F, -0.2F, -1F, -0.3F, -0.8F, -1F, -0.3F, -0.8F, -1F, -1F, -0.2F, -1F, -1F); // Box 385
		bodyModel[374].setRotationPoint(-48.8F, -8F, 5.5F);

		bodyModel[375].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 385
		bodyModel[375].setRotationPoint(-49.5F, -6.75F, -8.4F);

		bodyModel[376].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 385
		bodyModel[376].setRotationPoint(-49.5F, -6.75F, -6.7F);

		bodyModel[377].addShapeBox(0F, 0F, 0F, 1, 8, 4, 0F,-0.2F, -1.2F, -0.3F, -0.8F, -1.2F, -0.3F, -0.8F, -1.2F, -1F, -0.2F, -1.2F, -1F, -0.2F, -1F, -0.3F, -0.8F, -1F, -0.3F, -0.8F, -1F, -1F, -0.2F, -1F, -1F); // Box 385
		bodyModel[377].setRotationPoint(-48.8F, -8F, -8.7F);
	}
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

		for (int i = 0; i < 378; i++) {
			if (bodyModel[i].boxName != null && bodyModel[i].boxName.contains("lamp")) {
				Minecraft.getMinecraft().entityRenderer.disableLightmap();
				bodyModel[i].render(f5);
				Minecraft.getMinecraft().entityRenderer.enableLightmap();
			} else if (bodyModel[i].boxName != null && bodyModel[i].boxName.contains("cull")) {
				GL11.glDisable(GL11.GL_CULL_FACE);
				bodyModel[i].render(f5);
				GL11.glEnable(GL11.GL_CULL_FACE);
			} else {
				bodyModel[i].render(f5);
			}
		}
	}

}