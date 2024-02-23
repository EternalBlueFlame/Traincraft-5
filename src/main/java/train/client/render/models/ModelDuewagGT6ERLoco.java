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

public class ModelDuewagGT6ERLoco extends ModelConverter //Same as Filename
{
	int textureX = 512;
	int textureY = 256;

	public ModelDuewagGT6ERLoco() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[433];

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
		bodyModel[4] = new ModelRendererTurbo(this, 24, 0, textureX, textureY); // Box 66
		bodyModel[5] = new ModelRendererTurbo(this, 216, 0, textureX, textureY); // Box 67
		bodyModel[6] = new ModelRendererTurbo(this, 1, 177, textureX, textureY); // Box 572
		bodyModel[7] = new ModelRendererTurbo(this, 36, 177, textureX, textureY); // Box 575
		bodyModel[8] = new ModelRendererTurbo(this, 36, 177, textureX, textureY); // Box 576
		bodyModel[9] = new ModelRendererTurbo(this, 25, 177, textureX, textureY); // Box 577
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
		bodyModel[20] = new ModelRendererTurbo(this, 219, 19, textureX, textureY); // Box 186
		bodyModel[21] = new ModelRendererTurbo(this, 9, 25, textureX, textureY); // Box 82
		bodyModel[22] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // Box 58
		bodyModel[23] = new ModelRendererTurbo(this, 17, 25, textureX, textureY); // Box 86
		bodyModel[24] = new ModelRendererTurbo(this, 41, 9, textureX, textureY); // Box 179
		bodyModel[25] = new ModelRendererTurbo(this, 25, 25, textureX, textureY); // Box 180
		bodyModel[26] = new ModelRendererTurbo(this, 33, 25, textureX, textureY); // Box 181
		bodyModel[27] = new ModelRendererTurbo(this, 377, 9, textureX, textureY); // Box 182
		bodyModel[28] = new ModelRendererTurbo(this, 457, 9, textureX, textureY); // Box 183
		bodyModel[29] = new ModelRendererTurbo(this, 41, 23, textureX, textureY); // Box 161
		bodyModel[30] = new ModelRendererTurbo(this, 57, 25, textureX, textureY); // Box 139
		bodyModel[31] = new ModelRendererTurbo(this, 233, 25, textureX, textureY); // Box 141
		bodyModel[32] = new ModelRendererTurbo(this, 169, 25, textureX, textureY); // Box 156
		bodyModel[33] = new ModelRendererTurbo(this, 425, 25, textureX, textureY); // Box 294
		bodyModel[34] = new ModelRendererTurbo(this, 65, 33, textureX, textureY); // Box 306
		bodyModel[35] = new ModelRendererTurbo(this, 201, 33, textureX, textureY); // Box 0
		bodyModel[36] = new ModelRendererTurbo(this, 439, 23, textureX, textureY); // Box 4
		bodyModel[37] = new ModelRendererTurbo(this, 481, 25, textureX, textureY); // Box 5
		bodyModel[38] = new ModelRendererTurbo(this, 265, 33, textureX, textureY); // Box 6
		bodyModel[39] = new ModelRendererTurbo(this, 217, 1, textureX, textureY); // Box 294
		bodyModel[40] = new ModelRendererTurbo(this, 281, 33, textureX, textureY,"lamp"); // Box 170 lamp
		bodyModel[41] = new ModelRendererTurbo(this, 379, 22, textureX, textureY); // Box 97
		bodyModel[42] = new ModelRendererTurbo(this, 337, 33, textureX, textureY); // Box 98
		bodyModel[43] = new ModelRendererTurbo(this, 369, 33, textureX, textureY); // Box 99
		bodyModel[44] = new ModelRendererTurbo(this, 201, 1, textureX, textureY); // Box 100
		bodyModel[45] = new ModelRendererTurbo(this, 489, 1, textureX, textureY); // Box 103
		bodyModel[46] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Box 104
		bodyModel[47] = new ModelRendererTurbo(this, 41, 41, textureX, textureY); // Box 107
		bodyModel[48] = new ModelRendererTurbo(this, 481, 33, textureX, textureY); // Box 110
		bodyModel[49] = new ModelRendererTurbo(this, 114, 41, textureX, textureY); // Box 112
		bodyModel[50] = new ModelRendererTurbo(this, 385, 41, textureX, textureY); // Box 113
		bodyModel[51] = new ModelRendererTurbo(this, 444, 49, textureX, textureY); // Box 117
		bodyModel[52] = new ModelRendererTurbo(this, 91, 57, textureX, textureY); // Box 118
		bodyModel[53] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 171
		bodyModel[54] = new ModelRendererTurbo(this, 489, 9, textureX, textureY); // Box 173
		bodyModel[55] = new ModelRendererTurbo(this, 41, 25, textureX, textureY); // Box 176
		bodyModel[56] = new ModelRendererTurbo(this, 289, 81, textureX, textureY); // Box 179
		bodyModel[57] = new ModelRendererTurbo(this, 393, 81, textureX, textureY); // Box 180
		bodyModel[58] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 182
		bodyModel[59] = new ModelRendererTurbo(this, 251, 87, textureX, textureY); // Box 184
		bodyModel[60] = new ModelRendererTurbo(this, 337, 33, textureX, textureY); // Box 185
		bodyModel[61] = new ModelRendererTurbo(this, 497, 33, textureX, textureY); // Box 186
		bodyModel[62] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 226
		bodyModel[63] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 227
		bodyModel[64] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 228
		bodyModel[65] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 195
		bodyModel[66] = new ModelRendererTurbo(this, 113, 89, textureX, textureY); // Box 197
		bodyModel[67] = new ModelRendererTurbo(this, 361, 41, textureX, textureY); // Box 207
		bodyModel[68] = new ModelRendererTurbo(this, 169, 89, textureX, textureY); // Box 208
		bodyModel[69] = new ModelRendererTurbo(this, 337, 57, textureX, textureY); // Box 209
		bodyModel[70] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 8
		bodyModel[71] = new ModelRendererTurbo(this, 25, 97, textureX, textureY); // Box 9
		bodyModel[72] = new ModelRendererTurbo(this, 345, 25, textureX, textureY); // Box 10
		bodyModel[73] = new ModelRendererTurbo(this, 441, 33, textureX, textureY); // Box 11
		bodyModel[74] = new ModelRendererTurbo(this, 481, 33, textureX, textureY); // Box 12
		bodyModel[75] = new ModelRendererTurbo(this, 113, 49, textureX, textureY); // Box 13
		bodyModel[76] = new ModelRendererTurbo(this, 297, 57, textureX, textureY); // Box 15
		bodyModel[77] = new ModelRendererTurbo(this, 417, 41, textureX, textureY); // Box 19
		bodyModel[78] = new ModelRendererTurbo(this, 97, 49, textureX, textureY); // Box 21
		bodyModel[79] = new ModelRendererTurbo(this, 321, 57, textureX, textureY); // Box 24
		bodyModel[80] = new ModelRendererTurbo(this, 423, 46, textureX, textureY); // Box 124
		bodyModel[81] = new ModelRendererTurbo(this, 137, 84, textureX, textureY); // Box 125
		bodyModel[82] = new ModelRendererTurbo(this, 137, 84, textureX, textureY); // Box 127
		bodyModel[83] = new ModelRendererTurbo(this, 313, 65, textureX, textureY); // Box 128
		bodyModel[84] = new ModelRendererTurbo(this, 225, 89, textureX, textureY); // Box 129
		bodyModel[85] = new ModelRendererTurbo(this, 353, 65, textureX, textureY); // Box 130
		bodyModel[86] = new ModelRendererTurbo(this, 17, 97, textureX, textureY); // Box 131
		bodyModel[87] = new ModelRendererTurbo(this, 423, 54, textureX, textureY); // Box 126
		bodyModel[88] = new ModelRendererTurbo(this, 49, 97, textureX, textureY); // Box 449
		bodyModel[89] = new ModelRendererTurbo(this, 209, 97, textureX, textureY); // Box 450
		bodyModel[90] = new ModelRendererTurbo(this, 225, 97, textureX, textureY); // Box 385
		bodyModel[91] = new ModelRendererTurbo(this, 329, 97, textureX, textureY); // Box 386
		bodyModel[92] = new ModelRendererTurbo(this, 361, 97, textureX, textureY); // Box 388
		bodyModel[93] = new ModelRendererTurbo(this, 33, 73, textureX, textureY); // Box 389
		bodyModel[94] = new ModelRendererTurbo(this, 361, 57, textureX, textureY); // Box 390
		bodyModel[95] = new ModelRendererTurbo(this, 345, 97, textureX, textureY); // Box 391
		bodyModel[96] = new ModelRendererTurbo(this, 225, 25, textureX, textureY); // Box 392
		bodyModel[97] = new ModelRendererTurbo(this, 81, 49, textureX, textureY); // Box 393
		bodyModel[98] = new ModelRendererTurbo(this, 121, 49, textureX, textureY); // Box 394
		bodyModel[99] = new ModelRendererTurbo(this, 345, 73, textureX, textureY); // Box 399
		bodyModel[100] = new ModelRendererTurbo(this, 465, 97, textureX, textureY); // Box 400
		bodyModel[101] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Box 401
		bodyModel[102] = new ModelRendererTurbo(this, 241, 65, textureX, textureY); // Box 405
		bodyModel[103] = new ModelRendererTurbo(this, 419, 57, textureX, textureY); // Box 406
		bodyModel[104] = new ModelRendererTurbo(this, 73, 73, textureX, textureY); // Box 407
		bodyModel[105] = new ModelRendererTurbo(this, 377, 97, textureX, textureY); // Box 408
		bodyModel[106] = new ModelRendererTurbo(this, 1, 73, textureX, textureY); // Box 409
		bodyModel[107] = new ModelRendererTurbo(this, 481, 97, textureX, textureY); // Box 410
		bodyModel[108] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // Box 414
		bodyModel[109] = new ModelRendererTurbo(this, 89, 105, textureX, textureY); // Box 419
		bodyModel[110] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 420
		bodyModel[111] = new ModelRendererTurbo(this, 137, 73, textureX, textureY); // Box 421
		bodyModel[112] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // Box 422
		bodyModel[113] = new ModelRendererTurbo(this, 145, 105, textureX, textureY); // Box 426
		bodyModel[114] = new ModelRendererTurbo(this, 161, 105, textureX, textureY); // Box 427
		bodyModel[115] = new ModelRendererTurbo(this, 177, 105, textureX, textureY); // Box 431
		bodyModel[116] = new ModelRendererTurbo(this, 73, 81, textureX, textureY); // Box 432
		bodyModel[117] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 433
		bodyModel[118] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 434
		bodyModel[119] = new ModelRendererTurbo(this, 257, 81, textureX, textureY); // Box 435
		bodyModel[120] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 436
		bodyModel[121] = new ModelRendererTurbo(this, 91, 107, textureX, textureY); // Box 24
		bodyModel[122] = new ModelRendererTurbo(this, 179, 107, textureX, textureY); // Box 458
		bodyModel[123] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 568
		bodyModel[124] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 569
		bodyModel[125] = new ModelRendererTurbo(this, 345, 33, textureX, textureY); // Box 279
		bodyModel[126] = new ModelRendererTurbo(this, 121, 41, textureX, textureY); // Box 280
		bodyModel[127] = new ModelRendererTurbo(this, 179, 107, textureX, textureY); // Box 284
		bodyModel[128] = new ModelRendererTurbo(this, 161, 105, textureX, textureY); // Box 285
		bodyModel[129] = new ModelRendererTurbo(this, 361, 97, textureX, textureY); // Box 286
		bodyModel[130] = new ModelRendererTurbo(this, 1, 73, textureX, textureY); // Box 287
		bodyModel[131] = new ModelRendererTurbo(this, 419, 57, textureX, textureY); // Box 288
		bodyModel[132] = new ModelRendererTurbo(this, 73, 73, textureX, textureY); // Box 289
		bodyModel[133] = new ModelRendererTurbo(this, 241, 65, textureX, textureY); // Box 290
		bodyModel[134] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Box 291
		bodyModel[135] = new ModelRendererTurbo(this, 345, 73, textureX, textureY); // Box 292
		bodyModel[136] = new ModelRendererTurbo(this, 329, 97, textureX, textureY); // Box 293
		bodyModel[137] = new ModelRendererTurbo(this, 377, 97, textureX, textureY); // Box 294
		bodyModel[138] = new ModelRendererTurbo(this, 465, 97, textureX, textureY); // Box 295
		bodyModel[139] = new ModelRendererTurbo(this, 145, 105, textureX, textureY); // Box 296
		bodyModel[140] = new ModelRendererTurbo(this, 33, 73, textureX, textureY); // Box 297
		bodyModel[141] = new ModelRendererTurbo(this, 121, 49, textureX, textureY); // Box 298
		bodyModel[142] = new ModelRendererTurbo(this, 361, 57, textureX, textureY); // Box 299
		bodyModel[143] = new ModelRendererTurbo(this, 345, 97, textureX, textureY); // Box 300
		bodyModel[144] = new ModelRendererTurbo(this, 81, 49, textureX, textureY); // Box 301
		bodyModel[145] = new ModelRendererTurbo(this, 225, 25, textureX, textureY); // Box 302
		bodyModel[146] = new ModelRendererTurbo(this, 91, 107, textureX, textureY); // Box 303
		bodyModel[147] = new ModelRendererTurbo(this, 225, 97, textureX, textureY); // Box 304
		bodyModel[148] = new ModelRendererTurbo(this, 89, 105, textureX, textureY); // Box 305
		bodyModel[149] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // Box 306
		bodyModel[150] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 307
		bodyModel[151] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 308
		bodyModel[152] = new ModelRendererTurbo(this, 73, 81, textureX, textureY); // Box 309
		bodyModel[153] = new ModelRendererTurbo(this, 257, 81, textureX, textureY); // Box 310
		bodyModel[154] = new ModelRendererTurbo(this, 481, 97, textureX, textureY); // Box 311
		bodyModel[155] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 313
		bodyModel[156] = new ModelRendererTurbo(this, 177, 105, textureX, textureY); // Box 314
		bodyModel[157] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // Box 315
		bodyModel[158] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 316
		bodyModel[159] = new ModelRendererTurbo(this, 137, 73, textureX, textureY); // Box 317
		bodyModel[160] = new ModelRendererTurbo(this, 73, 89, textureX, textureY); // Box 318
		bodyModel[161] = new ModelRendererTurbo(this, 361, 89, textureX, textureY); // Box 319
		bodyModel[162] = new ModelRendererTurbo(this, 113, 89, textureX, textureY); // Box 320
		bodyModel[163] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 322
		bodyModel[164] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 323
		bodyModel[165] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 324
		bodyModel[166] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 325
		bodyModel[167] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 326
		bodyModel[168] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 327
		bodyModel[169] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 328
		bodyModel[170] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 329
		bodyModel[171] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 330
		bodyModel[172] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 331
		bodyModel[173] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 332
		bodyModel[174] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 333
		bodyModel[175] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 334
		bodyModel[176] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 335
		bodyModel[177] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 336
		bodyModel[178] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 337
		bodyModel[179] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 338
		bodyModel[180] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 339
		bodyModel[181] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 340
		bodyModel[182] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 341
		bodyModel[183] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 342
		bodyModel[184] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 343
		bodyModel[185] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 344
		bodyModel[186] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 345
		bodyModel[187] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 346
		bodyModel[188] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 347
		bodyModel[189] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 348
		bodyModel[190] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 349
		bodyModel[191] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 376
		bodyModel[192] = new ModelRendererTurbo(this, 313, 57, textureX, textureY); // Box 379
		bodyModel[193] = new ModelRendererTurbo(this, 222, 91, textureX, textureY); // Box 328 lamp
		bodyModel[194] = new ModelRendererTurbo(this, 444, 62, textureX, textureY); // Box 333
		bodyModel[195] = new ModelRendererTurbo(this, 169, 38, textureX, textureY); // Box 336
		bodyModel[196] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // Sta_Light_1
		bodyModel[197] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // Sta_Light_2
		bodyModel[198] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // Sta_Light_4
		bodyModel[199] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // Sta_Light_3
		bodyModel[200] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // Box 381 lamp
		bodyModel[201] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // Box 382 lamp
		bodyModel[202] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // Box 383 lamp
		bodyModel[203] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // Box 384 lamp
		bodyModel[204] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // Box 385 lamp
		bodyModel[205] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // Box 386 lamp
		bodyModel[206] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // Box 387 lamp
		bodyModel[207] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // Box 388 lamp
		bodyModel[208] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // Box 389 lamp
		bodyModel[209] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // Box 390 lamp
		bodyModel[210] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // Box 391 lamp
		bodyModel[211] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // Box 392 lamp
		bodyModel[212] = new ModelRendererTurbo(this, 198, 65, textureX, textureY); // Box 361
		bodyModel[213] = new ModelRendererTurbo(this, 198, 76, textureX, textureY); // Box 362
		bodyModel[214] = new ModelRendererTurbo(this, 198, 52, textureX, textureY); // Box 363
		bodyModel[215] = new ModelRendererTurbo(this, 170, 76, textureX, textureY); // Box 364
		bodyModel[216] = new ModelRendererTurbo(this, 170, 65, textureX, textureY); // Box 365
		bodyModel[217] = new ModelRendererTurbo(this, 170, 52, textureX, textureY); // Box 366
		bodyModel[218] = new ModelRendererTurbo(this, 6, 54, textureX, textureY); // Box 367
		bodyModel[219] = new ModelRendererTurbo(this, 226, 67, textureX, textureY); // Box 368
		bodyModel[220] = new ModelRendererTurbo(this, 226, 78, textureX, textureY); // Box 369
		bodyModel[221] = new ModelRendererTurbo(this, 226, 54, textureX, textureY); // Box 370
		bodyModel[222] = new ModelRendererTurbo(this, 226, 51, textureX, textureY); // Box 371
		bodyModel[223] = new ModelRendererTurbo(this, 259, 51, textureX, textureY); // Box 372
		bodyModel[224] = new ModelRendererTurbo(this, 259, 54, textureX, textureY); // Box 373
		bodyModel[225] = new ModelRendererTurbo(this, 259, 67, textureX, textureY); // Box 374
		bodyModel[226] = new ModelRendererTurbo(this, 259, 78, textureX, textureY); // Box 375
		bodyModel[227] = new ModelRendererTurbo(this, 153, 46, textureX, textureY); // Box 376
		bodyModel[228] = new ModelRendererTurbo(this, 109, 60, textureX, textureY); // Box 377
		bodyModel[229] = new ModelRendererTurbo(this, 153, 41, textureX, textureY); // Box 378
		bodyModel[230] = new ModelRendererTurbo(this, 1, 140, textureX, textureY); // Box 13
		bodyModel[231] = new ModelRendererTurbo(this, 17, 140, textureX, textureY); // Box 16
		bodyModel[232] = new ModelRendererTurbo(this, 33, 140, textureX, textureY); // Box 17
		bodyModel[233] = new ModelRendererTurbo(this, 49, 140, textureX, textureY); // Box 18
		bodyModel[234] = new ModelRendererTurbo(this, 65, 140, textureX, textureY); // Box 19
		bodyModel[235] = new ModelRendererTurbo(this, 81, 140, textureX, textureY); // Box 20
		bodyModel[236] = new ModelRendererTurbo(this, 93, 140, textureX, textureY); // Box 21
		bodyModel[237] = new ModelRendererTurbo(this, 121, 140, textureX, textureY); // Box 22
		bodyModel[238] = new ModelRendererTurbo(this, 145, 140, textureX, textureY); // Box 24
		bodyModel[239] = new ModelRendererTurbo(this, 169, 140, textureX, textureY); // Box 25
		bodyModel[240] = new ModelRendererTurbo(this, 193, 140, textureX, textureY); // Box 26
		bodyModel[241] = new ModelRendererTurbo(this, 97, 140, textureX, textureY); // Box 27
		bodyModel[242] = new ModelRendererTurbo(this, 137, 140, textureX, textureY); // Box 29
		bodyModel[243] = new ModelRendererTurbo(this, 217, 140, textureX, textureY); // Box 30
		bodyModel[244] = new ModelRendererTurbo(this, 233, 140, textureX, textureY); // Box 31
		bodyModel[245] = new ModelRendererTurbo(this, 131, 151, textureX, textureY); // Box 40
		bodyModel[246] = new ModelRendererTurbo(this, 140, 151, textureX, textureY); // Box 41
		bodyModel[247] = new ModelRendererTurbo(this, 149, 151, textureX, textureY); // Box 42
		bodyModel[248] = new ModelRendererTurbo(this, 158, 151, textureX, textureY); // Box 43
		bodyModel[249] = new ModelRendererTurbo(this, 414, 65, textureX, textureY); // Box 61
		bodyModel[250] = new ModelRendererTurbo(this, 389, 65, textureX, textureY); // Box 88
		bodyModel[251] = new ModelRendererTurbo(this, 389, 58, textureX, textureY); // Box 172
		bodyModel[252] = new ModelRendererTurbo(this, 396, 58, textureX, textureY); // Box 176
		bodyModel[253] = new ModelRendererTurbo(this, 405, 58, textureX, textureY); // Box 404
		bodyModel[254] = new ModelRendererTurbo(this, 414, 58, textureX, textureY); // Box 405
		bodyModel[255] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 406
		bodyModel[256] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 407
		bodyModel[257] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 408
		bodyModel[258] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 409
		bodyModel[259] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 410
		bodyModel[260] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 411
		bodyModel[261] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 412
		bodyModel[262] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 413
		bodyModel[263] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 414
		bodyModel[264] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 415
		bodyModel[265] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 416
		bodyModel[266] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 417
		bodyModel[267] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 418
		bodyModel[268] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 419
		bodyModel[269] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 420
		bodyModel[270] = new ModelRendererTurbo(this, 180, 174, textureX, textureY); // Box 46
		bodyModel[271] = new ModelRendererTurbo(this, 113, 174, textureX, textureY); // Box 186
		bodyModel[272] = new ModelRendererTurbo(this, 104, 179, textureX, textureY); // Box 339
		bodyModel[273] = new ModelRendererTurbo(this, 95, 176, textureX, textureY); // Box 340
		bodyModel[274] = new ModelRendererTurbo(this, 148, 173, textureX, textureY); // Box 310
		bodyModel[275] = new ModelRendererTurbo(this, 141, 173, textureX, textureY); // Box 312
		bodyModel[276] = new ModelRendererTurbo(this, 116, 164, textureX, textureY); // Box 313
		bodyModel[277] = new ModelRendererTurbo(this, 476, 100, textureX, textureY); // Box 314
		bodyModel[278] = new ModelRendererTurbo(this, 10, 12, textureX, textureY); // Box 317
		bodyModel[279] = new ModelRendererTurbo(this, 383, 32, textureX, textureY); // Box 318
		bodyModel[280] = new ModelRendererTurbo(this, 372, 57, textureX, textureY); // Box 319
		bodyModel[281] = new ModelRendererTurbo(this, 372, 69, textureX, textureY); // Box 320
		bodyModel[282] = new ModelRendererTurbo(this, 1, 20, textureX, textureY); // Box 322
		bodyModel[283] = new ModelRendererTurbo(this, 94, 121, textureX, textureY); // Box 187
		bodyModel[284] = new ModelRendererTurbo(this, 86, 127, textureX, textureY); // Box 327
		bodyModel[285] = new ModelRendererTurbo(this, 60, 80, textureX, textureY); // Box 282
		bodyModel[286] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 331
		bodyModel[287] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 332
		bodyModel[288] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 333
		bodyModel[289] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 334
		bodyModel[290] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 335
		bodyModel[291] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 336
		bodyModel[292] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 337
		bodyModel[293] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 338
		bodyModel[294] = new ModelRendererTurbo(this, 49, 44, textureX, textureY); // Box 342
		bodyModel[295] = new ModelRendererTurbo(this, 49, 124, textureX, textureY); // Box 170
		bodyModel[296] = new ModelRendererTurbo(this, 57, 124, textureX, textureY); // Box 171
		bodyModel[297] = new ModelRendererTurbo(this, 42, 126, textureX, textureY); // Box 437
		bodyModel[298] = new ModelRendererTurbo(this, 60, 126, textureX, textureY); // Box 438
		bodyModel[299] = new ModelRendererTurbo(this, 51, 127, textureX, textureY,"lamp"); // Box 169 lamp
		bodyModel[300] = new ModelRendererTurbo(this, 29, 9, textureX, textureY); // Box 314
		bodyModel[301] = new ModelRendererTurbo(this, 29, 9, textureX, textureY); // Box 315
		bodyModel[302] = new ModelRendererTurbo(this, 29, 9, textureX, textureY); // Box 318
		bodyModel[303] = new ModelRendererTurbo(this, 29, 9, textureX, textureY); // Box 319
		bodyModel[304] = new ModelRendererTurbo(this, 252, 241, textureX, textureY); // Box 318
		bodyModel[305] = new ModelRendererTurbo(this, 49, 235, textureX, textureY); // Box 319
		bodyModel[306] = new ModelRendererTurbo(this, 121, 242, textureX, textureY); // Box 320
		bodyModel[307] = new ModelRendererTurbo(this, 121, 233, textureX, textureY); // Box 321
		bodyModel[308] = new ModelRendererTurbo(this, 65, 231, textureX, textureY); // Box 322
		bodyModel[309] = new ModelRendererTurbo(this, 121, 244, textureX, textureY); // Box 323
		bodyModel[310] = new ModelRendererTurbo(this, 121, 235, textureX, textureY); // Box 324
		bodyModel[311] = new ModelRendererTurbo(this, 121, 234, textureX, textureY); // Box 325
		bodyModel[312] = new ModelRendererTurbo(this, 97, 235, textureX, textureY); // Box 326
		bodyModel[313] = new ModelRendererTurbo(this, 121, 237, textureX, textureY); // Box 327
		bodyModel[314] = new ModelRendererTurbo(this, 256, 232, textureX, textureY); // Box 328
		bodyModel[315] = new ModelRendererTurbo(this, 104, 239, textureX, textureY); // Box 329
		bodyModel[316] = new ModelRendererTurbo(this, 113, 235, textureX, textureY); // Box 330
		bodyModel[317] = new ModelRendererTurbo(this, 1, 235, textureX, textureY); // Box 331
		bodyModel[318] = new ModelRendererTurbo(this, 25, 235, textureX, textureY); // Box 332
		bodyModel[319] = new ModelRendererTurbo(this, 104, 230, textureX, textureY); // Box 333
		bodyModel[320] = new ModelRendererTurbo(this, 121, 240, textureX, textureY); // Box 334
		bodyModel[321] = new ModelRendererTurbo(this, 121, 232, textureX, textureY); // Box 335
		bodyModel[322] = new ModelRendererTurbo(this, 3, 52, textureX, textureY); // Box 336
		bodyModel[323] = new ModelRendererTurbo(this, 6, 60, textureX, textureY); // Box 337
		bodyModel[324] = new ModelRendererTurbo(this, 2, 25, textureX, textureY); // Box 341
		bodyModel[325] = new ModelRendererTurbo(this, 2, 25, textureX, textureY); // Box 343
		bodyModel[326] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 344
		bodyModel[327] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 345
		bodyModel[328] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 346
		bodyModel[329] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 347
		bodyModel[330] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Box 356 lamp
		bodyModel[331] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Box 357 lamp
		bodyModel[332] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Box 358 lamp
		bodyModel[333] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Box 359 lamp
		bodyModel[334] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Box 359 lamp
		bodyModel[335] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Box 360 lamp
		bodyModel[336] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 361
		bodyModel[337] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 362
		bodyModel[338] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Box 363 lamp
		bodyModel[339] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Box 364 lamp
		bodyModel[340] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 365
		bodyModel[341] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 366
		bodyModel[342] = new ModelRendererTurbo(this, 141, 169, textureX, textureY); // Box 359
		bodyModel[343] = new ModelRendererTurbo(this, 140, 185, textureX, textureY); // Box 361
		bodyModel[344] = new ModelRendererTurbo(this, 147, 185, textureX, textureY); // Box 365
		bodyModel[345] = new ModelRendererTurbo(this, 148, 169, textureX, textureY); // Box 366
		bodyModel[346] = new ModelRendererTurbo(this, 283, 121, textureX, textureY); // Box 367
		bodyModel[347] = new ModelRendererTurbo(this, 286, 121, textureX, textureY); // Box 368
		bodyModel[348] = new ModelRendererTurbo(this, 291, 121, textureX, textureY); // Box 369
		bodyModel[349] = new ModelRendererTurbo(this, 303, 121, textureX, textureY); // Box 370
		bodyModel[350] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 371
		bodyModel[351] = new ModelRendererTurbo(this, 283, 121, textureX, textureY); // Box 372
		bodyModel[352] = new ModelRendererTurbo(this, 286, 121, textureX, textureY); // Box 373
		bodyModel[353] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 376
		bodyModel[354] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 377
		bodyModel[355] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 378
		bodyModel[356] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 379
		bodyModel[357] = new ModelRendererTurbo(this, 173, 130, textureX, textureY); // Box 58
		bodyModel[358] = new ModelRendererTurbo(this, 185, 125, textureX, textureY); // Box 59
		bodyModel[359] = new ModelRendererTurbo(this, 118, 125, textureX, textureY); // Box 322
		bodyModel[360] = new ModelRendererTurbo(this, 173, 126, textureX, textureY); // Box 323
		bodyModel[361] = new ModelRendererTurbo(this, 2, 25, textureX, textureY); // Box 379
		bodyModel[362] = new ModelRendererTurbo(this, 1, 188, textureX, textureY); // Box 377
		bodyModel[363] = new ModelRendererTurbo(this, 1, 188, textureX, textureY); // Box 378
		bodyModel[364] = new ModelRendererTurbo(this, 1, 188, textureX, textureY); // Box 379
		bodyModel[365] = new ModelRendererTurbo(this, 19, 198, textureX, textureY); // Box 380
		bodyModel[366] = new ModelRendererTurbo(this, 2, 203, textureX, textureY); // Box 381
		bodyModel[367] = new ModelRendererTurbo(this, 2, 196, textureX, textureY); // Box 382
		bodyModel[368] = new ModelRendererTurbo(this, 211, 173, textureX, textureY); // Box 383
		bodyModel[369] = new ModelRendererTurbo(this, 481, 80, textureX, textureY); // Box 583
		bodyModel[370] = new ModelRendererTurbo(this, 481, 80, textureX, textureY); // Box 584
		bodyModel[371] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 385
		bodyModel[372] = new ModelRendererTurbo(this, 329, 1, textureX, textureY); // Box 386
		bodyModel[373] = new ModelRendererTurbo(this, 233, 1, textureX, textureY); // Box 389
		bodyModel[374] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 390
		bodyModel[375] = new ModelRendererTurbo(this, 25, 47, textureX, textureY); // Box 389
		bodyModel[376] = new ModelRendererTurbo(this, 1, 47, textureX, textureY); // Box 390
		bodyModel[377] = new ModelRendererTurbo(this, 495, 115, textureX, textureY); // Box 391
		bodyModel[378] = new ModelRendererTurbo(this, 199, 173, textureX, textureY); // Box 391
		bodyModel[379] = new ModelRendererTurbo(this, 199, 166, textureX, textureY); // Box 392
		bodyModel[380] = new ModelRendererTurbo(this, 115, 127, textureX, textureY); // Box 395
		bodyModel[381] = new ModelRendererTurbo(this, 107, 121, textureX, textureY); // Box 396
		bodyModel[382] = new ModelRendererTurbo(this, 137, 247, textureX, textureY); // Box 239
		bodyModel[383] = new ModelRendererTurbo(this, 321, 247, textureX, textureY); // Box 325
		bodyModel[384] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 393
		bodyModel[385] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 394
		bodyModel[386] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 395
		bodyModel[387] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 396
		bodyModel[388] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 397
		bodyModel[389] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 398
		bodyModel[390] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 399
		bodyModel[391] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 400
		bodyModel[392] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 401
		bodyModel[393] = new ModelRendererTurbo(this, 275, 125, textureX, textureY); // Box 615
		bodyModel[394] = new ModelRendererTurbo(this, 288, 125, textureX, textureY); // Box 617
		bodyModel[395] = new ModelRendererTurbo(this, 267, 128, textureX, textureY); // Box 618
		bodyModel[396] = new ModelRendererTurbo(this, 270, 125, textureX, textureY); // Box 619
		bodyModel[397] = new ModelRendererTurbo(this, 301, 125, textureX, textureY); // Box 620
		bodyModel[398] = new ModelRendererTurbo(this, 270, 131, textureX, textureY); // Box 414
		bodyModel[399] = new ModelRendererTurbo(this, 275, 131, textureX, textureY); // Box 415
		bodyModel[400] = new ModelRendererTurbo(this, 288, 131, textureX, textureY); // Box 416
		bodyModel[401] = new ModelRendererTurbo(this, 253, 124, textureX, textureY); // Box 417
		bodyModel[402] = new ModelRendererTurbo(this, 262, 135, textureX, textureY); // Box 411
		bodyModel[403] = new ModelRendererTurbo(this, 255, 135, textureX, textureY); // Box 412
		bodyModel[404] = new ModelRendererTurbo(this, 81, 57, textureX, textureY); // Box 413
		bodyModel[405] = new ModelRendererTurbo(this, 81, 57, textureX, textureY); // Box 414
		bodyModel[406] = new ModelRendererTurbo(this, 81, 57, textureX, textureY); // Box 415
		bodyModel[407] = new ModelRendererTurbo(this, 81, 57, textureX, textureY); // Box 416
		bodyModel[408] = new ModelRendererTurbo(this, 81, 65, textureX, textureY); // Box 417
		bodyModel[409] = new ModelRendererTurbo(this, 81, 65, textureX, textureY); // Box 418
		bodyModel[410] = new ModelRendererTurbo(this, 81, 65, textureX, textureY); // Box 419
		bodyModel[411] = new ModelRendererTurbo(this, 81, 65, textureX, textureY); // Box 420
		bodyModel[412] = new ModelRendererTurbo(this, 2, 164, textureX, textureY); // Box 318
		bodyModel[413] = new ModelRendererTurbo(this, 2, 164, textureX, textureY); // Box 319
		bodyModel[414] = new ModelRendererTurbo(this, 2, 164, textureX, textureY); // Box 320
		bodyModel[415] = new ModelRendererTurbo(this, 0, 88, textureX, textureY); // Box 649
		bodyModel[416] = new ModelRendererTurbo(this, 0, 94, textureX, textureY); // Box 650
		bodyModel[417] = new ModelRendererTurbo(this, 495, 101, textureX, textureY); // Box 432
		bodyModel[418] = new ModelRendererTurbo(this, 10, 4, textureX, textureY); // Box 433
		bodyModel[419] = new ModelRendererTurbo(this, 212, 166, textureX, textureY); // Box 434
		bodyModel[420] = new ModelRendererTurbo(this, 10, 85, textureX, textureY); // Box 639
		bodyModel[421] = new ModelRendererTurbo(this, 16, 83, textureX, textureY); // Box 640
		bodyModel[422] = new ModelRendererTurbo(this, 16, 83, textureX, textureY); // Box 641
		bodyModel[423] = new ModelRendererTurbo(this, 4, 82, textureX, textureY,"lamp"); // Box 642
		bodyModel[424] = new ModelRendererTurbo(this, 10, 85, textureX, textureY); // Box 643
		bodyModel[425] = new ModelRendererTurbo(this, 10, 85, textureX, textureY); // Box 644
		bodyModel[426] = new ModelRendererTurbo(this, 4, 82, textureX, textureY,"lamp"); // Box 645
		bodyModel[427] = new ModelRendererTurbo(this, 16, 83, textureX, textureY); // Box 646
		bodyModel[428] = new ModelRendererTurbo(this, 10, 85, textureX, textureY); // Box 647
		bodyModel[429] = new ModelRendererTurbo(this, 16, 83, textureX, textureY); // Box 648
		bodyModel[430] = new ModelRendererTurbo(this, 213, 179, textureX, textureY); // Box 431
		bodyModel[431] = new ModelRendererTurbo(this, 105, 169, textureX, textureY); // Box 432
		bodyModel[432] = new ModelRendererTurbo(this, 105, 176, textureX, textureY); // Box 433

		bodyModel[0].addShapeBox(0F, 0F, 0F, 4, 1, 17, 0F,1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 1.5F, 0F, 0.5F, 1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 1.5F, 0F, 0.5F); // Box 9
		bodyModel[0].setRotationPoint(-21F, 4F, -9F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 43, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 62
		bodyModel[1].setRotationPoint(-16.5F, -19F, -8F);

		bodyModel[2].addShapeBox(0F, 0F, 0F, 5, 2, 19, 0F,0F, 0F, -1.8F, 0F, 0F, -0.45F, 0F, 0F, -0.2F, 0F, 0F, -1.85F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -1.2F); // Box 63
		bodyModel[2].setRotationPoint(-26.5F, -18F, -9.5F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 2, 2, 15, 0F,0F, 0F, -0.75F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, -0.75F, 0F, 0F, 0.25F, 0F, 0F, 1F, 0F, 0F, 0.8F, 0F, 0F, 0.25F); // Box 65
		bodyModel[3].setRotationPoint(-28.5F, -18F, -7.5F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 1, 2, 14, 0F,0F, -0.5F, -1.5F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, -0.5F, -1.55F, 0.3F, 0F, 0F, 0F, 0F, 0.75F, 0F, 0F, 0.75F, 0.3F, 0F, 0F); // Box 66
		bodyModel[4].setRotationPoint(-29.5F, -18F, -7F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 1, 1, 12, 0F,-0.35F, -0.3F, -2.05F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.55F, -0.35F, -0.3F, -2.05F, -0.3F, 0F, -1.05F, -0.3F, 0F, 1F, -0.3F, 0F, 1F, -0.3F, 0F, -1.05F); // Box 67
		bodyModel[5].setRotationPoint(-30.5F, -17F, -6F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, -0.375F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F); // Box 572
		bodyModel[6].setRotationPoint(-0.5F, -21F, 5.25F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 20, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 575
		bodyModel[7].setRotationPoint(1.5F, -21F, -7.5F);

		bodyModel[8].addShapeBox(0F, 0F, 0F, 20, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 576
		bodyModel[8].setRotationPoint(1.5F, -21F, 6.5F);

		bodyModel[9].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,-0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.375F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 577
		bodyModel[9].setRotationPoint(-0.5F, -21F, -7.25F);

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

		bodyModel[16].addShapeBox(0F, 0F, 0F, 5, 1, 17, 0F,0F, -0.5F, -1F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.5F, -1F, 0F, 0F, -0.8F, 0F, 0F, 0.55F, 0F, 0F, 0.8F, 0F, 0F, -0.85F); // Box 145
		bodyModel[16].setRotationPoint(-26.5F, -19F, -8.5F);

		bodyModel[17].addShapeBox(0F, 0F, 0F, 5, 2, 19, 0F,0F, 0F, 0.05F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -0.7F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 154
		bodyModel[17].setRotationPoint(-21.5F, -18F, -9F);

		bodyModel[18].addShapeBox(0F, 0F, 0F, 5, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1.05F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0.3F); // Box 165
		bodyModel[18].setRotationPoint(-21.5F, -19F, -8F);

		bodyModel[19].addShapeBox(0F, 0F, 0F, 2, 21, 1, 0F,0F, 1F, 0.25F, 0F, 1F, -0.5F, 0F, 1F, 0.3F, 0F, 1F, -0.25F, 0F, 0F, 0.25F, 0F, 0F, -0.5F, 0F, 0F, 0.3F, 0F, 0F, -0.25F); // Box 184
		bodyModel[19].setRotationPoint(-28.5F, -15F, 7F);

		bodyModel[20].addShapeBox(0F, 0F, 0F, 1, 1, 11, 0F,-0.5F, 0F, -2F, 0F, 0F, -0.5F, 0F, 0F, -1.5F, -0.5F, 0F, -3F, -0.5F, 0.5F, -2F, 0F, 0.5F, -0.5F, 0F, 0.5F, -1.5F, -0.5F, 0.5F, -3F); // Box 186
		bodyModel[20].setRotationPoint(-32.7F, 4F, -5F);

		bodyModel[21].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.25F, 0.5F, 1F, -1F, 0.5F, 1F, 0.5F, 0F, 1F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -1F, 0.5F, 0F, 0.5F, 0F, 0F, -0.25F); // Box 82
		bodyModel[21].setRotationPoint(-26.5F, -14F, 8F);

		bodyModel[22].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -0.7F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -0.7F); // Box 58
		bodyModel[22].setRotationPoint(-26.5F, -16F, 6F);

		bodyModel[23].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.25F, 0.5F, 1F, -1F, 0.5F, 1F, 0.5F, 0F, 1F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -1F, 0.5F, 0F, 0.5F, 0F, 0F, -0.25F); // Box 86
		bodyModel[23].setRotationPoint(-24F, -14F, 8.75F);

		bodyModel[24].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 179
		bodyModel[24].setRotationPoint(-21.5F, -16F, 6.5F);

		bodyModel[25].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -1F, 0.5F, 1F, -1F, 0.5F, 1F, 0.5F, 0F, 1F, 0.5F, 0F, 0F, -1F, 0.5F, 0F, -1F, 0.5F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 180
		bodyModel[25].setRotationPoint(-21.5F, -14F, 8.75F);

		bodyModel[26].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.75F, 0.5F, 1F, -0.75F, 0.5F, 1F, 0.25F, 0F, 1F, 0.25F, 0F, 0F, -0.75F, 0.5F, 0F, -0.75F, 0.5F, 0F, 0.25F, 0F, 0F, 0.25F); // Box 181
		bodyModel[26].setRotationPoint(-19F, -14F, 9F);

		bodyModel[27].addShapeBox(0F, 1F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 182
		bodyModel[27].setRotationPoint(-21.5F, 5F, 6.5F);

		bodyModel[28].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -0.5F); // Box 183
		bodyModel[28].setRotationPoint(-26.5F, 6F, 6F);

		bodyModel[29].addShapeBox(0F, 0F, 0F, 1, 3, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0.5F, 0.44F, 0F, 0F, 0.75F, 0F, 0F, 0.75F, 0.5F, 0.5F, 0.44F, 0.5F); // Box 161
		bodyModel[29].setRotationPoint(-29.5F, -16.2F, -5.25F);

		bodyModel[30].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 139
		bodyModel[30].setRotationPoint(-16.5F, -16F, 9F);

		bodyModel[31].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 141
		bodyModel[31].setRotationPoint(-16.5F, -16F, -10F);

		bodyModel[32].addShapeBox(0F, 0F, 0F, 21, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 156
		bodyModel[32].setRotationPoint(-16.5F, -14.5F, 9F);

		bodyModel[33].addShapeBox(0F, 0F, 0F, 10, 3, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 294
		bodyModel[33].setRotationPoint(4.5F, -18F, 6F);

		bodyModel[34].addShapeBox(0F, 0F, 0F, 37, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F); // Box 306
		bodyModel[34].setRotationPoint(-13.5F, -18F, -1F);

		bodyModel[35].addShapeBox(0F, 0F, 0F, 21, 1, 17, 0F,0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 1F, 0F, 0F, 1F); // Box 0
		bodyModel[35].setRotationPoint(-17.5F, 4F, -9F);

		bodyModel[36].addShapeBox(0F, 0F, 0F, 10, 3, 15, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 4
		bodyModel[36].setRotationPoint(4.5F, 4F, -8F);

		bodyModel[37].addBox(0F, 0F, 0F, 10, 1, 3, 0F); // Box 5
		bodyModel[37].setRotationPoint(4.5F, 6F, 7.1F);

		bodyModel[38].addBox(0F, 0F, 0F, 10, 1, 1, 0F); // Box 6
		bodyModel[38].setRotationPoint(4.5F, 4F, 7F);

		bodyModel[39].addShapeBox(0F, 0F, 0F, 1, 3, 3, 0F,0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 2F, -1F, 0F, 2F, -1F, 0F, 0F, 0F, 0F); // Box 294
		bodyModel[39].setRotationPoint(-30.19F, -19F, -1.5F);

		bodyModel[40].addShapeBox(0F, 0F, 0F, 1, 9, 9, 0F,0F, 0F, -6F, 0F, -0.3F, -6F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, -6F, -6F, 0F, -6F, -6F, 0F, -6F, 0F, 0F, -6F, 0F); // Box 170 lamp
		bodyModel[40].setRotationPoint(-30.2F, -19F, -7.5F);

		bodyModel[41].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.2F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 97
		bodyModel[41].setRotationPoint(-21.5F, -16F, -9.5F);

		bodyModel[42].addBox(0F, 0F, 0F, 1, 2, 14, 0F); // Box 98
		bodyModel[42].setRotationPoint(-23.5F, 5F, -8.5F);

		bodyModel[43].addShapeBox(0F, 0F, 0F, 5, 21, 1, 0F,0F, 1F, -1.5F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1.5F); // Box 99
		bodyModel[43].setRotationPoint(-26.5F, -15F, -10F);

		bodyModel[44].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, 0.25F, 0F, 0F, -0.3F, 0F, 0F, 0.3F, 0F, 0F, -0.25F, 0F, 0F, 0.25F, 0F, 0F, -0.25F, 0F, -0.2F, 0.05F, 0F, -0.2F, -0.5F); // Box 100
		bodyModel[44].setRotationPoint(-28.5F, 6F, 7F);

		bodyModel[45].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.25F, 0F, -0.2F, -0.45F, 0F, -0.2F, 0.3F, 0F, 0F, -0.5F, 0F, 0F, 0.25F); // Box 103
		bodyModel[45].setRotationPoint(-28.5F, 6F, -8F);

		bodyModel[46].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1.5F, 0F, -0.2F, -1.7F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 1.5F); // Box 104
		bodyModel[46].setRotationPoint(-26.5F, 6F, -10F);

		bodyModel[47].addShapeBox(0F, 0F, 0F, 21, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 107
		bodyModel[47].setRotationPoint(-16.5F, 6F, 9.1F);

		bodyModel[48].addShapeBox(0F, 0F, 0F, 2, 1, 11, 0F,-1.15F, -0.5F, -0.75F, -0.45F, -0.5F, -0.75F, -0.45F, -0.5F, -0.75F, -1.1F, -0.5F, -0.75F, -1.1F, 0.4F, -0.75F, -0.5F, 0.45F, -0.75F, -0.5F, 0.45F, -0.75F, -1.1F, 0.4F, -0.75F); // Box 110
		bodyModel[48].setRotationPoint(-31.5F, -14.2F, -5.5F);

		bodyModel[49].addShapeBox(0F, 0F, 0F, 4, 1, 13, 0F,0F, 0F, 1F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 0F, 0F); // Box 112
		bodyModel[49].setRotationPoint(-26.5F, 4F, -6.5F);

		bodyModel[50].addShapeBox(0F, 0F, 0F, 3, 2, 14, 0F,3F, 0F, 2.25F, 0F, 0F, 2.5F, 0F, 0F, 0F, 3F, 0F, -0.5F, 3F, 0F, 2.25F, 0F, 0F, 2.5F, 0F, 0F, 0F, 3F, 0F, -0.5F); // Box 113
		bodyModel[50].setRotationPoint(-19.5F, 5F, -6.5F);

		bodyModel[51].addShapeBox(0F, 0F, 0F, 31, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 117
		bodyModel[51].setRotationPoint(-16.5F, -15F, -10F);

		bodyModel[52].addShapeBox(0F, 0F, 0F, 31, 1, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 118
		bodyModel[52].setRotationPoint(-16.5F, 6F, -10.1F);

		bodyModel[53].addShapeBox(0F, 0F, 0F, 3, 1, 10, 0F,0.75F, 0F, -0.25F, 0F, 0F, 2F, 0F, 0F, 3F, 0.75F, 0F, 1F, 0.75F, -0.25F, -0.25F, 0F, 0F, 2F, 0F, 0F, 3F, 0.75F, -0.25F, 1F); // Box 171
		bodyModel[53].setRotationPoint(-29.5F, 4F, -5.5F);

		bodyModel[54].addShapeBox(0F, 0F, 0F, 4, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -0.5F); // Box 173
		bodyModel[54].setRotationPoint(-26.5F, 5F, 5.5F);

		bodyModel[55].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.3F, 0F, 0F, -0.2F); // Box 176
		bodyModel[55].setRotationPoint(-27.5F, 4.9F, 6F);

		bodyModel[56].addShapeBox(0F, 0F, 0F, 1, 2, 17, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 179
		bodyModel[56].setRotationPoint(14.5F, 5F, -8.5F);

		bodyModel[57].addShapeBox(0F, 0F, 0F, 1, 2, 17, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 180
		bodyModel[57].setRotationPoint(3.5F, 5F, -8.5F);

		bodyModel[58].addShapeBox(0F, 0F, 0F, 7, 7, 5, 0F,0F, 0F, -4F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -4F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 182
		bodyModel[58].setRotationPoint(-29.5F, -3F, -10F);

		bodyModel[59].addShapeBox(0F, 0F, 0F, 2, 2, 9, 0F,0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 184
		bodyModel[59].setRotationPoint(-29.5F, -4F, -5F);

		bodyModel[60].addShapeBox(0F, 0F, 0F, 1, 8, 3, 0F,-1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F); // Box 185
		bodyModel[60].setRotationPoint(-27.5F, -3.5F, 4F);

		bodyModel[61].addShapeBox(0F, 0F, 0F, 2, 7, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 186
		bodyModel[61].setRotationPoint(-29.5F, -3F, 4F);

		bodyModel[62].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 226
		bodyModel[62].setRotationPoint(2.5F, -5F, -8.5F);

		bodyModel[63].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 227
		bodyModel[63].setRotationPoint(-0.5F, 0F, -8.5F);

		bodyModel[64].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 228
		bodyModel[64].setRotationPoint(1.75F, 0.5F, -6F);

		bodyModel[65].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 195
		bodyModel[65].setRotationPoint(3.25F, -18F, -5.5F);

		bodyModel[66].addBox(0F, 0F, 0F, 0, 22, 4, 0F); // Box 197
		bodyModel[66].setRotationPoint(14.5F, -17F, 4.5F);

		bodyModel[67].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 207
		bodyModel[67].setRotationPoint(-25.25F, 0.5F, -1F);

		bodyModel[68].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 208
		bodyModel[68].setRotationPoint(-26.5F, 0F, -2.5F);

		bodyModel[69].addShapeBox(0F, 0F, 0F, 1, 4, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 209
		bodyModel[69].setRotationPoint(-23.5F, -4F, -2.5F);

		bodyModel[70].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 8
		bodyModel[70].setRotationPoint(-9F, -33.75F, -4.75F);

		bodyModel[71].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 9
		bodyModel[71].setRotationPoint(-7F, -33.75F, -4.75F);

		bodyModel[72].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 10
		bodyModel[72].setRotationPoint(-9F, -32.75F, -5F);

		bodyModel[73].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 11
		bodyModel[73].setRotationPoint(-9F, -32.75F, 4F);

		bodyModel[74].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 12
		bodyModel[74].setRotationPoint(-7F, -33.75F, -6.75F);

		bodyModel[75].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 13
		bodyModel[75].setRotationPoint(-9F, -33.75F, 5.25F);

		bodyModel[76].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 15
		bodyModel[76].setRotationPoint(-9F, -33.75F, -6.75F);

		bodyModel[77].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 19
		bodyModel[77].setRotationPoint(-10F, -20F, -4.25F);

		bodyModel[78].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 21
		bodyModel[78].setRotationPoint(-10F, -20F, 3.25F);

		bodyModel[79].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 24
		bodyModel[79].setRotationPoint(-7F, -33.75F, 5.25F);

		bodyModel[80].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 124
		bodyModel[80].setRotationPoint(-17F, -21F, -5F);

		bodyModel[81].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 125
		bodyModel[81].setRotationPoint(-17F, -30.25F, -5F);

		bodyModel[82].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 127
		bodyModel[82].setRotationPoint(-17F, -30.25F, 5F);

		bodyModel[83].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 128
		bodyModel[83].setRotationPoint(-5F, -21F, 5F);

		bodyModel[84].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 129
		bodyModel[84].setRotationPoint(-6F, -30.25F, 5F);

		bodyModel[85].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 130
		bodyModel[85].setRotationPoint(-5F, -21F, -5F);

		bodyModel[86].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 131
		bodyModel[86].setRotationPoint(-6F, -30.25F, -5F);

		bodyModel[87].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 126
		bodyModel[87].setRotationPoint(-17F, -21F, 5F);

		bodyModel[88].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 449
		bodyModel[88].setRotationPoint(-10F, -21F, -5F);

		bodyModel[89].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 450
		bodyModel[89].setRotationPoint(-6F, -21F, -5F);

		bodyModel[90].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 385
		bodyModel[90].setRotationPoint(-5F, 6F, -3F);

		bodyModel[91].addBox(0F, 0F, 0F, 8, 3, 4, 0F); // Box 386
		bodyModel[91].setRotationPoint(-13F, 6F, -2F);

		bodyModel[92].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 388
		bodyModel[92].setRotationPoint(-16F, 6F, -3F);

		bodyModel[93].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 389
		bodyModel[93].setRotationPoint(-6.15F, 5F, -5F);

		bodyModel[94].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 390
		bodyModel[94].setRotationPoint(-4.65F, 6.5F, -6F);

		bodyModel[95].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 391
		bodyModel[95].setRotationPoint(-4.15F, 7F, -5F);

		bodyModel[96].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 392
		bodyModel[96].setRotationPoint(-2.95F, 6.15F, -6.5F);

		bodyModel[97].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 393
		bodyModel[97].setRotationPoint(-5.65F, 8.75F, -6.5F);

		bodyModel[98].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 394
		bodyModel[98].setRotationPoint(-6.35F, 6.15F, -6.5F);

		bodyModel[99].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 399
		bodyModel[99].setRotationPoint(-11.5F, 6.75F, -5F);

		bodyModel[100].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 400
		bodyModel[100].setRotationPoint(-11F, 6F, -4F);

		bodyModel[101].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 401
		bodyModel[101].setRotationPoint(-12.5F, 7.5F, -6.5F);

		bodyModel[102].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 405
		bodyModel[102].setRotationPoint(-13.8F, 6.15F, -6.5F);

		bodyModel[103].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 406
		bodyModel[103].setRotationPoint(-16.5F, 8.75F, -6.5F);

		bodyModel[104].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 407
		bodyModel[104].setRotationPoint(-15.5F, 6.5F, -6F);

		bodyModel[105].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 408
		bodyModel[105].setRotationPoint(-15F, 7F, -5F);

		bodyModel[106].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 409
		bodyModel[106].setRotationPoint(-17.2F, 6.15F, -6.5F);

		bodyModel[107].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 410
		bodyModel[107].setRotationPoint(-12.5F, 7.5F, 5.5F);

		bodyModel[108].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 414
		bodyModel[108].setRotationPoint(-11.5F, 6.75F, 4F);

		bodyModel[109].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 419
		bodyModel[109].setRotationPoint(-6.15F, 5F, 5F);

		bodyModel[110].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 420
		bodyModel[110].setRotationPoint(-5.65F, 8.75F, 5F);

		bodyModel[111].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 421
		bodyModel[111].setRotationPoint(-4.65F, 6.5F, 4.5F);

		bodyModel[112].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 422
		bodyModel[112].setRotationPoint(-2.95F, 6.15F, 5F);

		bodyModel[113].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 426
		bodyModel[113].setRotationPoint(-8F, 6F, -4F);

		bodyModel[114].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 427
		bodyModel[114].setRotationPoint(-17F, 5F, -5F);

		bodyModel[115].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 431
		bodyModel[115].setRotationPoint(-17F, 5F, 5F);

		bodyModel[116].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 432
		bodyModel[116].setRotationPoint(-15.5F, 6.5F, 4.5F);

		bodyModel[117].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 433
		bodyModel[117].setRotationPoint(-16.5F, 8.75F, 5F);

		bodyModel[118].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 434
		bodyModel[118].setRotationPoint(-13.8F, 6.15F, 5F);

		bodyModel[119].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 435
		bodyModel[119].setRotationPoint(-17.2F, 6.15F, 5F);

		bodyModel[120].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 436
		bodyModel[120].setRotationPoint(-6.35F, 6.15F, 5F);

		bodyModel[121].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 24
		bodyModel[121].setRotationPoint(-1F, 8.25F, -6F);

		bodyModel[122].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 458
		bodyModel[122].setRotationPoint(-18F, 8.25F, -6F);

		bodyModel[123].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, -0.5F, -0.25F, -0.5F, 0F, -0.375F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 568
		bodyModel[123].setRotationPoint(21.5F, -21F, -7.25F);

		bodyModel[124].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, 0F, -0.375F, 0F, -0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F); // Box 569
		bodyModel[124].setRotationPoint(21.5F, -21F, 5.25F);

		bodyModel[125].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -1.5F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 2.5F, 0F, 0.5F, -1.5F, 0F, 0.5F, 0.25F, 0F, 0.5F, 0F, 0F, 0.5F, 2.5F); // Box 279
		bodyModel[125].setRotationPoint(-31.7F, 4F, -6F);

		bodyModel[126].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 2.5F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -1.5F, 0F, 0.5F, 2.5F, 0F, 0.5F, 0F, 0F, 0.5F, 0.25F, 0F, 0.5F, -1.5F); // Box 280
		bodyModel[126].setRotationPoint(-31.7F, 4F, 5F);

		bodyModel[127].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 284
		bodyModel[127].setRotationPoint(19.5F, 8.25F, -6F);

		bodyModel[128].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 285
		bodyModel[128].setRotationPoint(20.5F, 5F, -5F);

		bodyModel[129].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 286
		bodyModel[129].setRotationPoint(21.5F, 6F, -3F);

		bodyModel[130].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 287
		bodyModel[130].setRotationPoint(20.3F, 6.15F, -6.5F);

		bodyModel[131].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 288
		bodyModel[131].setRotationPoint(21F, 8.75F, -6.5F);

		bodyModel[132].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 289
		bodyModel[132].setRotationPoint(22F, 6.5F, -6F);

		bodyModel[133].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 290
		bodyModel[133].setRotationPoint(23.7F, 6.15F, -6.5F);

		bodyModel[134].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 291
		bodyModel[134].setRotationPoint(24.9F, 7.5F, -6.5F);

		bodyModel[135].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 292
		bodyModel[135].setRotationPoint(26F, 6.75F, -5F);

		bodyModel[136].addBox(0F, 0F, 0F, 8, 3, 4, 0F); // Box 293
		bodyModel[136].setRotationPoint(24.5F, 6F, -2F);

		bodyModel[137].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 294
		bodyModel[137].setRotationPoint(22.5F, 7F, -5F);

		bodyModel[138].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 295
		bodyModel[138].setRotationPoint(26.5F, 6F, -4F);

		bodyModel[139].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 296
		bodyModel[139].setRotationPoint(29.5F, 6F, -4F);

		bodyModel[140].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 297
		bodyModel[140].setRotationPoint(31.35F, 5F, -5F);

		bodyModel[141].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 298
		bodyModel[141].setRotationPoint(31.15F, 6.15F, -6.5F);

		bodyModel[142].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 299
		bodyModel[142].setRotationPoint(32.85F, 6.5F, -6F);

		bodyModel[143].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 300
		bodyModel[143].setRotationPoint(33.35F, 7F, -5F);

		bodyModel[144].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 301
		bodyModel[144].setRotationPoint(31.85F, 8.75F, -6.5F);

		bodyModel[145].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 302
		bodyModel[145].setRotationPoint(34.55F, 6.15F, -6.5F);

		bodyModel[146].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 303
		bodyModel[146].setRotationPoint(36.5F, 8.25F, -6F);

		bodyModel[147].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 304
		bodyModel[147].setRotationPoint(32.5F, 6F, -3F);

		bodyModel[148].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 305
		bodyModel[148].setRotationPoint(31.35F, 5F, 5F);

		bodyModel[149].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 306
		bodyModel[149].setRotationPoint(26F, 6.75F, 4F);

		bodyModel[150].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 307
		bodyModel[150].setRotationPoint(21F, 8.75F, 5F);

		bodyModel[151].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 308
		bodyModel[151].setRotationPoint(23.7F, 6.15F, 5F);

		bodyModel[152].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 309
		bodyModel[152].setRotationPoint(22F, 6.5F, 4.5F);

		bodyModel[153].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 310
		bodyModel[153].setRotationPoint(20.3F, 6.15F, 5F);

		bodyModel[154].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 311
		bodyModel[154].setRotationPoint(24.9F, 7.5F, 5.5F);

		bodyModel[155].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 313
		bodyModel[155].setRotationPoint(31.85F, 8.75F, 5F);

		bodyModel[156].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 314
		bodyModel[156].setRotationPoint(20.5F, 5F, 5F);

		bodyModel[157].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 315
		bodyModel[157].setRotationPoint(34.55F, 6.15F, 5F);

		bodyModel[158].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 316
		bodyModel[158].setRotationPoint(31.15F, 6.15F, 5F);

		bodyModel[159].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 317
		bodyModel[159].setRotationPoint(32.85F, 6.5F, 4.5F);

		bodyModel[160].addBox(0F, 0F, 0F, 0, 22, 4, 0F); // Box 318
		bodyModel[160].setRotationPoint(-16.4F, -17F, -8.5F);

		bodyModel[161].addBox(0F, 0F, 0F, 19, 0, 3, 0F); // Box 319
		bodyModel[161].setRotationPoint(-15F, -14.5F, -8.5F);

		bodyModel[162].addBox(0F, 0F, 0F, 19, 0, 3, 0F); // Box 320
		bodyModel[162].setRotationPoint(-16.1F, -14.5F, 6F);

		bodyModel[163].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 322
		bodyModel[163].setRotationPoint(-4.25F, 0.5F, -6F);

		bodyModel[164].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 323
		bodyModel[164].setRotationPoint(-3.5F, -5F, -8.5F);

		bodyModel[165].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 324
		bodyModel[165].setRotationPoint(-6.5F, 0F, -8.5F);

		bodyModel[166].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 325
		bodyModel[166].setRotationPoint(-2.75F, -18F, -5.5F);

		bodyModel[167].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 326
		bodyModel[167].setRotationPoint(-9.25F, 0.5F, -6F);

		bodyModel[168].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 327
		bodyModel[168].setRotationPoint(-8.5F, -5F, -8.5F);

		bodyModel[169].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 328
		bodyModel[169].setRotationPoint(-11.5F, 0F, -8.5F);

		bodyModel[170].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 329
		bodyModel[170].setRotationPoint(-7.75F, -18F, -5.5F);

		bodyModel[171].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 330
		bodyModel[171].setRotationPoint(-13.25F, 0.5F, -6F);

		bodyModel[172].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 331
		bodyModel[172].setRotationPoint(-12.5F, -5F, -8.5F);

		bodyModel[173].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 332
		bodyModel[173].setRotationPoint(-15.5F, 0F, -8.5F);

		bodyModel[174].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 333
		bodyModel[174].setRotationPoint(-11.75F, -18F, -5.5F);

		bodyModel[175].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 334
		bodyModel[175].setRotationPoint(-13.25F, 0.5F, 5F);

		bodyModel[176].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 335
		bodyModel[176].setRotationPoint(-12.5F, -5F, 4.5F);

		bodyModel[177].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 336
		bodyModel[177].setRotationPoint(-15.5F, 0F, 4.5F);

		bodyModel[178].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 337
		bodyModel[178].setRotationPoint(-11.75F, -18F, 4.5F);

		bodyModel[179].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 338
		bodyModel[179].setRotationPoint(-9.25F, 0.5F, 5F);

		bodyModel[180].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 339
		bodyModel[180].setRotationPoint(-11.5F, 0F, 4.5F);

		bodyModel[181].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 340
		bodyModel[181].setRotationPoint(-8.5F, -5F, 4.5F);

		bodyModel[182].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 341
		bodyModel[182].setRotationPoint(-6.5F, 0F, 4.5F);

		bodyModel[183].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 342
		bodyModel[183].setRotationPoint(-3.5F, -5F, 4.5F);

		bodyModel[184].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 343
		bodyModel[184].setRotationPoint(-4.25F, 0.5F, 5F);

		bodyModel[185].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 344
		bodyModel[185].setRotationPoint(-0.5F, 0F, 4.5F);

		bodyModel[186].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 345
		bodyModel[186].setRotationPoint(1.75F, 0.5F, 5F);

		bodyModel[187].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 346
		bodyModel[187].setRotationPoint(2.5F, -5F, 4.5F);

		bodyModel[188].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 347
		bodyModel[188].setRotationPoint(-7.75F, -18F, 4.5F);

		bodyModel[189].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 348
		bodyModel[189].setRotationPoint(-2.75F, -18F, 4.5F);

		bodyModel[190].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 349
		bodyModel[190].setRotationPoint(3.25F, -18F, 4.5F);

		bodyModel[191].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 376
		bodyModel[191].setRotationPoint(19.25F, -18F, 4F);

		bodyModel[192].addShapeBox(0F, 0F, 0F, 1, 2, 4, 0F,0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 379
		bodyModel[192].setRotationPoint(-23F, -6F, -2.5F);

		bodyModel[193].addShapeBox(0F, 0F, 0F, 1, 6, 23, 0F,0F, 0F, 0.05F, 0F, 0F, 0.25F, 0F, 0F, -13.25F, 0F, 0F, -13.45F, 0F, -4.2F, 0.05F, 0F, -4.2F, 0.25F, 0F, -4.2F, -13.25F, 0F, -4.2F, -13.45F); // Box 328 lamp
		bodyModel[193].setRotationPoint(-30.1F, -15.5F, -4.75F);

		bodyModel[194].addShapeBox(0F, 0F, 0F, 31, 9, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 333
		bodyModel[194].setRotationPoint(-16.5F, -3.5F, -10.1F);

		bodyModel[195].addShapeBox(0F, 0F, 0F, 21, 9, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 336
		bodyModel[195].setRotationPoint(-16.5F, -3F, 9.1F);

		bodyModel[196].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Sta_Light_1
		bodyModel[196].setRotationPoint(-32.1F, -1F, -0.88F);

		bodyModel[197].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F); // Sta_Light_2
		bodyModel[197].setRotationPoint(-32.1F, -1F, -0.129999999999999F);

		bodyModel[198].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F); // Sta_Light_4
		bodyModel[198].setRotationPoint(-32.1F, -0.25F, -0.129999999999999F);

		bodyModel[199].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F); // Sta_Light_3
		bodyModel[199].setRotationPoint(-32.1F, -0.25F, -0.88F);

		bodyModel[200].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Box 381 lamp
		bodyModel[200].setRotationPoint(-32.2F, 1.25F, -0.88F);

		bodyModel[201].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Box 382 lamp
		bodyModel[201].setRotationPoint(-32.2F, 1.25F, -0.129999999999999F);

		bodyModel[202].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Box 383 lamp
		bodyModel[202].setRotationPoint(-32.2F, 0.5F, -0.129999999999999F);

		bodyModel[203].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 384 lamp
		bodyModel[203].setRotationPoint(-32.2F, 0.5F, -0.88F);

		bodyModel[204].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Box 385 lamp
		bodyModel[204].setRotationPoint(-32.1F, 0.75F, 0.5F);

		bodyModel[205].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Box 386 lamp
		bodyModel[205].setRotationPoint(-32.1F, 0.75F, 1.25F);

		bodyModel[206].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Box 387 lamp
		bodyModel[206].setRotationPoint(-32.1F, 0F, 1.25F);

		bodyModel[207].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 388 lamp
		bodyModel[207].setRotationPoint(-32.1F, 0F, 0.5F);

		bodyModel[208].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Box 389 lamp
		bodyModel[208].setRotationPoint(-32.1F, 0.75F, -2.25F);

		bodyModel[209].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Box 390 lamp
		bodyModel[209].setRotationPoint(-32.1F, 0.75F, -1.5F);

		bodyModel[210].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Box 391 lamp
		bodyModel[210].setRotationPoint(-32.1F, 0F, -1.5F);

		bodyModel[211].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 392 lamp
		bodyModel[211].setRotationPoint(-32.1F, 0F, -2.25F);

		bodyModel[212].addShapeBox(0F, 0F, 0F, 12, 9, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 361
		bodyModel[212].setRotationPoint(14.5F, -3.5F, -10.1F);

		bodyModel[213].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 362
		bodyModel[213].setRotationPoint(14.5F, 6F, -10.1F);

		bodyModel[214].addShapeBox(0F, 0F, 0F, 12, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 363
		bodyModel[214].setRotationPoint(14.5F, -15F, -10F);

		bodyModel[215].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 364
		bodyModel[215].setRotationPoint(14.5F, 6F, 9.1F);

		bodyModel[216].addShapeBox(0F, 0F, 0F, 12, 9, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 365
		bodyModel[216].setRotationPoint(14.5F, -3F, 9.1F);

		bodyModel[217].addShapeBox(0F, 0F, 0F, 12, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 366
		bodyModel[217].setRotationPoint(14.5F, -14.5F, 9F);

		bodyModel[218].addShapeBox(0F, 0F, 0F, 12, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 367
		bodyModel[218].setRotationPoint(14.5F, 4F, -9F);

		bodyModel[219].addShapeBox(0F, 0F, 0F, 4, 9, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 368
		bodyModel[219].setRotationPoint(26.5F, -3F, 8.6F);

		bodyModel[220].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 369
		bodyModel[220].setRotationPoint(26.5F, 6F, 8.6F);

		bodyModel[221].addShapeBox(0F, 0F, 0F, 4, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 370
		bodyModel[221].setRotationPoint(26.5F, -14.5F, 8.5F);

		bodyModel[222].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 371
		bodyModel[222].setRotationPoint(26.5F, -16F, 8.5F);

		bodyModel[223].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 372
		bodyModel[223].setRotationPoint(26.5F, -16F, -9.5F);

		bodyModel[224].addShapeBox(0F, 0F, 0F, 4, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 373
		bodyModel[224].setRotationPoint(26.5F, -15F, -9.5F);

		bodyModel[225].addShapeBox(0F, 0F, 0F, 4, 9, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 374
		bodyModel[225].setRotationPoint(26.5F, -3.5F, -9.6F);

		bodyModel[226].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 375
		bodyModel[226].setRotationPoint(26.5F, 6F, -9.6F);

		bodyModel[227].addShapeBox(0F, 0F, 0F, 4, 2, 2, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F); // Box 376
		bodyModel[227].setRotationPoint(26.5F, -18F, -9.5F);

		bodyModel[228].addShapeBox(0F, 0F, 0F, 4, 1, 16, 0F,0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -1F, 0F, -0.5F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 377
		bodyModel[228].setRotationPoint(26.5F, -19F, -7.5F);

		bodyModel[229].addShapeBox(0F, 0F, 0F, 4, 2, 2, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 378
		bodyModel[229].setRotationPoint(26.5F, -18F, 7.5F);

		bodyModel[230].addShapeBox(0F, 0F, 0F, 3, 2, 3, 0F,0F, -1F, 1F, 0F, -1F, 1F, 0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 3F, 0F, -1F, 3F); // Box 13
		bodyModel[230].setRotationPoint(27F, -18F, -6F);

		bodyModel[231].addShapeBox(0F, 0F, 0F, 3, 4, 1, 0F,0F, -2F, -1F, 0F, -2F, -1F, 0F, -3F, 1F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 16
		bodyModel[231].setRotationPoint(27F, -19F, -8F);

		bodyModel[232].addShapeBox(0F, 0F, 0F, 3, 4, 1, 0F,0F, -3F, 1F, 0F, -3F, 1F, 0F, -2F, -1F, 0F, -2F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 17
		bodyModel[232].setRotationPoint(27F, -19F, 7F);

		bodyModel[233].addShapeBox(0F, 0F, 0F, 3, 2, 3, 0F,0F, 0F, 3F, 0F, 0F, 3F, 0F, -1F, 1F, 0F, -1F, 1F, 0F, -1F, 3F, 0F, -1F, 3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 18
		bodyModel[233].setRotationPoint(27F, -18F, 3F);

		bodyModel[234].addBox(0F, 0F, 0F, 3, 19, 1, 0F); // Box 19
		bodyModel[234].setRotationPoint(27F, -15F, 7F);

		bodyModel[235].addBox(0F, 0F, 0F, 3, 19, 1, 0F); // Box 20
		bodyModel[235].setRotationPoint(27F, -15F, -8F);

		bodyModel[236].addBox(0F, 0F, 0F, 4, 1, 14, 0F); // Box 21
		bodyModel[236].setRotationPoint(26.5F, 3.99F, -7F);

		bodyModel[237].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F); // Box 22
		bodyModel[237].setRotationPoint(30.5F, 3.99F, -7F);

		bodyModel[238].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F); // Box 24
		bodyModel[238].setRotationPoint(30.5F, 3.99F, 0F);

		bodyModel[239].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F); // Box 25
		bodyModel[239].setRotationPoint(23.5F, 3.99F, 0F);

		bodyModel[240].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F); // Box 26
		bodyModel[240].setRotationPoint(23.5F, 3.99F, -7F);

		bodyModel[241].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 27
		bodyModel[241].setRotationPoint(27F, -4F, 5.5F);

		bodyModel[242].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 29
		bodyModel[242].setRotationPoint(27F, -4F, -6.5F);

		bodyModel[243].addShapeBox(0F, 0F, 0F, 3, 7, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 30
		bodyModel[243].setRotationPoint(27F, -3F, -6.5F);

		bodyModel[244].addShapeBox(0F, 0F, 0F, 3, 7, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 31
		bodyModel[244].setRotationPoint(27F, -3F, 5.5F);

		bodyModel[245].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F); // Box 40
		bodyModel[245].setRotationPoint(25F, -3F, -6.5F);

		bodyModel[246].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F); // Box 41
		bodyModel[246].setRotationPoint(30F, -3F, -6F);

		bodyModel[247].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F); // Box 42
		bodyModel[247].setRotationPoint(25F, -3F, 5.5F);

		bodyModel[248].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F); // Box 43
		bodyModel[248].setRotationPoint(30F, -3F, 5F);

		bodyModel[249].addBox(0F, 0F, 0F, 1, 19, 2, 0F); // Box 61
		bodyModel[249].setRotationPoint(25.5F, -15F, 7F);

		bodyModel[250].addBox(0F, 0F, 0F, 1, 19, 2, 0F); // Box 88
		bodyModel[250].setRotationPoint(25.5F, -15F, -9F);

		bodyModel[251].addShapeBox(0F, 0F, 0F, 1, 4, 2, 0F,0F, -3F, 1F, 0F, -3F, 1F, 0F, -1F, -0.5F, 0F, -1F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 172
		bodyModel[251].setRotationPoint(25.5F, -19F, 7F);

		bodyModel[252].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 2.5F, 0F, 0F, 2.5F, 0F, -1F, 3F, 0F, -1F, 3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 176
		bodyModel[252].setRotationPoint(25.5F, -18F, 3F);

		bodyModel[253].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 2.5F, 0F, 0F, 2.5F, 0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 3F, 0F, -1F, 3F); // Box 404
		bodyModel[253].setRotationPoint(25.5F, -18F, -6F);

		bodyModel[254].addShapeBox(0F, 0F, 0F, 1, 4, 2, 0F,0F, -1F, -0.5F, 0F, -1F, -0.5F, 0F, -3F, 1F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 405
		bodyModel[254].setRotationPoint(25.5F, -19F, -9F);

		bodyModel[255].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 406
		bodyModel[255].setRotationPoint(17.75F, 0.5F, -6F);

		bodyModel[256].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 407
		bodyModel[256].setRotationPoint(18.5F, -5F, -8.5F);

		bodyModel[257].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 408
		bodyModel[257].setRotationPoint(20.5F, 0F, -8.5F);

		bodyModel[258].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 409
		bodyModel[258].setRotationPoint(22.75F, 0.5F, -6F);

		bodyModel[259].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 410
		bodyModel[259].setRotationPoint(23.5F, -5F, -8.5F);

		bodyModel[260].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 411
		bodyModel[260].setRotationPoint(19.25F, -18F, -5.5F);

		bodyModel[261].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 412
		bodyModel[261].setRotationPoint(24.25F, -18F, -5.5F);

		bodyModel[262].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 413
		bodyModel[262].setRotationPoint(15.5F, 0F, 4.5F);

		bodyModel[263].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 414
		bodyModel[263].setRotationPoint(18.5F, -5F, 4.5F);

		bodyModel[264].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 415
		bodyModel[264].setRotationPoint(17.75F, 0.5F, 5F);

		bodyModel[265].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 416
		bodyModel[265].setRotationPoint(23.5F, -5F, 4.5F);

		bodyModel[266].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 417
		bodyModel[266].setRotationPoint(20.5F, 0F, 4.5F);

		bodyModel[267].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 418
		bodyModel[267].setRotationPoint(22.75F, 0.5F, 5F);

		bodyModel[268].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 419
		bodyModel[268].setRotationPoint(15.5F, 0F, -8.5F);

		bodyModel[269].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 420
		bodyModel[269].setRotationPoint(24.25F, -18F, 4F);

		bodyModel[270].addShapeBox(0F, 0F, 0F, 1, 10, 10, 0F,0.2F, 0F, -0.25F, 0F, 0F, 2F, 0F, 0F, 2F, 0.2F, 0F, -0.25F, 0.2F, 0F, -0.25F, 0F, 0F, 2F, 0F, 0F, 2F, 0.2F, 0F, -0.25F); // Box 46
		bodyModel[270].setRotationPoint(-31F, -4.5F, -5F);

		bodyModel[271].addShapeBox(0F, 0F, 0F, 2, 10, 10, 0F,-0.38F, 0F, -3.5F, -1F, 0F, -0.25F, -1F, 0F, -0.25F, -0.38F, 0F, -3.5F, -0.38F, 0F, -3.5F, -1F, 0F, -0.25F, -1F, 0F, -0.25F, -0.38F, 0F, -3.5F); // Box 186
		bodyModel[271].setRotationPoint(-32.2F, -4.5F, -5F);

		bodyModel[272].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.2F, 0.25F, -2F, -0.5F, 0.25F, -2F, -0.5F, 0.25F, 0F, -0.5F, 0.25F, -0.5F, 1F, 0F, -0.35F, -0.5F, 0F, -2F, -0.5F, 0F, 0F, 0F, 0F, -0.75F); // Box 339
		bodyModel[272].setRotationPoint(-30F, -5.5F, 4.75F);

		bodyModel[273].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.8F, -0.4F, -2.45F, -1F, -0.4F, -2.3F, -0.5F, -0.4F, 0F, -0.9F, -0.4F, -0.25F, -0.2F, 1F, -1.95F, -1F, 1F, -2F, -0.5F, 1F, 0F, -0.5F, 1F, -0.5F); // Box 340
		bodyModel[273].setRotationPoint(-30F, -7.75F, 4.75F);

		bodyModel[274].addShapeBox(0F, 0F, 0F, 1, 10, 1, 0F,0F, 0F, 0.5F, 0.5F, 0F, 1.25F, 0.5F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.5F, 0F, 1.25F, 0.5F, 0F, -1F, 0F, 0F, 0F); // Box 310
		bodyModel[274].setRotationPoint(-30F, -4.5F, -6.5F);

		bodyModel[275].addShapeBox(0F, 0F, 0F, 1, 10, 1, 0F,0F, 0F, 0F, 0.5F, 0F, -1F, 0.5F, 0F, 1F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0.5F, 0F, -1F, 0.5F, 0F, 1F, 0F, 0F, 0.25F); // Box 312
		bodyModel[275].setRotationPoint(-30F, -4.5F, 5.75F);

		bodyModel[276].addShapeBox(0F, 0F, 0F, 2, 2, 3, 0F,0.3F, -0.875F, -0.5F, -0.1F, -1.25F, 0.25F, -0.5F, -0.55F, 0F, -0.08F, -0.75F, -0.6F, -0.15F, -0.6F, -1.95F, -0.2F, -0.2F, -0.55F, -0.5F, -0.2F, 0F, -0.25F, -0.6F, -0.5F); // Box 313
		bodyModel[276].setRotationPoint(-30F, -13.7F, 4.75F);

		bodyModel[277].addShapeBox(0F, 0F, 0F, 2, 1, 11, 0F,-1.1F, -0.5F, -0.75F, -0.5F, -0.5F, -0.75F, -0.5F, -0.5F, -0.75F, -1.1F, -0.5F, -0.75F, -1.05F, 0F, -0.75F, -0.543F, 0F, -0.75F, -0.54F, 0F, -0.75F, -1.075F, 0F, -0.75F); // Box 314
		bodyModel[277].setRotationPoint(-31.3F, -16.5F, -5.5F);

		bodyModel[278].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.21F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0.25F, 0F, 0.35F, 0F, 0.05F, 0F, 0F, 0F, 0F, 0F, -0.15F, 1F, 0.425F, 0.05F, 0.4F); // Box 317
		bodyModel[278].setRotationPoint(-29.5F, -16F, 5.75F);

		bodyModel[279].addShapeBox(0F, 0F, 0F, 2, 21, 1, 0F,0F, 1F, -1.25F, 0F, 1F, -0.5F, 0F, 1F, 0.5F, 0F, 1F, 1.25F, 0F, 0F, -1.25F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 1.25F); // Box 318
		bodyModel[279].setRotationPoint(-28.5F, -15F, -9F);

		bodyModel[280].addShapeBox(0F, 0F, 0F, 5, 11, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F, 0.6F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F); // Box 319
		bodyModel[280].setRotationPoint(-21.5F, -15F, -9.5F);

		bodyModel[281].addShapeBox(0F, 0F, 0F, 5, 9, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.6F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F, 0.7F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F); // Box 320
		bodyModel[281].setRotationPoint(-21.5F, -3.5F, -9.5F);

		bodyModel[282].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.3F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0.1F); // Box 322
		bodyModel[282].setRotationPoint(-21.5F, 6F, -10.1F);

		bodyModel[283].addShapeBox(0F, 0F, 0F, 1, 5, 5, 0F,-0.7F, 0.5F, -0.25F, -0.1F, 0.5F, -0.25F, 0.8F, 0.25F, 0.7F, -1.5F, 0.25F, 1.35F, 1.25F, 0.85F, -0.25F, -1.75F, 0.85F, -0.25F, -0.5F, 0.85F, 2.2F, -0.4F, 0.85F, 2.2F); // Box 187
		bodyModel[283].setRotationPoint(-29.5F, -13.2F, 0F);

		bodyModel[284].addShapeBox(0F, 0F, 0F, 1, 3, 5, 0F,0.25F, 0.35F, -0.25F, -0.75F, 0.35F, -0.25F, 0.5F, 0.35F, 2.2F, -1.4F, 0.35F, 2.2F, 1.3F, 0.1F, -0.25F, -1.7F, 0.1F, -0.25F, -0.6F, 0.1F, 1F, 0F, 0.1F, 1F); // Box 327
		bodyModel[284].setRotationPoint(-30.5F, -7F, 0F);

		bodyModel[285].addShapeBox(0F, 0F, 0F, 1, 7, 1, 0F,-2F, 2F, -0.7F, 1.4F, 2F, -0.7F, 1.4F, 2F, 0.3F, -2F, 2F, 0.3F, 0.5F, -0.5F, -0.7F, -1F, -0.5F, -0.7F, -1F, -0.5F, 0.3F, 0.5F, -0.5F, 0.3F); // Box 282
		bodyModel[285].setRotationPoint(-31.15F, -11F, -1F);

		bodyModel[286].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 331
		bodyModel[286].setRotationPoint(7.75F, 0.5F, -6F);

		bodyModel[287].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 332
		bodyModel[287].setRotationPoint(8.5F, -5F, -8.5F);

		bodyModel[288].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 333
		bodyModel[288].setRotationPoint(5.5F, 0F, -8.5F);

		bodyModel[289].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 334
		bodyModel[289].setRotationPoint(9.25F, -18F, -5.5F);

		bodyModel[290].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 335
		bodyModel[290].setRotationPoint(12.75F, 0.5F, -6F);

		bodyModel[291].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 336
		bodyModel[291].setRotationPoint(13.5F, -5F, -8.5F);

		bodyModel[292].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 337
		bodyModel[292].setRotationPoint(10.5F, 0F, -8.5F);

		bodyModel[293].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 338
		bodyModel[293].setRotationPoint(14.25F, -18F, -5.5F);

		bodyModel[294].addShapeBox(0F, 0F, 0F, 1, 3, 14, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0.55F, 0F, 0F, 0.75F, 0F, 0F, 0.75F, 0F, 0.1F, 0.55F, 0F); // Box 342
		bodyModel[294].setRotationPoint(-28.4F, -16F, -7F);

		bodyModel[295].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.85F, -0.5F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.75F, -0.25F, 0F, -0.6F, -0.5F, 0F, -0.25F, -0.25F, 0F, 0F, -0.25F, 0F, -0.5F, -0.25F); // Box 170
		bodyModel[295].setRotationPoint(-31.95F, -0.9F, -1.75F);

		bodyModel[296].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.75F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.85F, -0.5F, 0F, -0.5F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.6F, -0.5F); // Box 171
		bodyModel[296].setRotationPoint(-31.95F, -0.9F, 0.75F);

		bodyModel[297].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, -0.9F, -0.4F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.6F, 0F, -0.9F, -0.6F, 0F, 0F, -0.4F, 0F, 0F, -0.5F, 0F, 0F, -0.6F, 0F, 0F, -0.6F); // Box 437
		bodyModel[297].setRotationPoint(-31.95F, -1.05F, -1.4F);

		bodyModel[298].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0F, -0.4F, 0F, 0F, -0.5F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.9F, -0.4F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.6F, 0F, -0.9F, -0.6F); // Box 438
		bodyModel[298].setRotationPoint(-31.95F, 1.5F, -1.4F);

		bodyModel[299].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, -1.25F, -0.5F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F, 0F, -1.25F, -0.5F, 0F, 0.75F, -0.5F, 0F, 1F, -0.5F, 0F, 1F, -0.5F, 0F, 0.75F, -0.5F); // Box 169 lamp
		bodyModel[299].setRotationPoint(-31.95F, -1.3F, -1.5F);

		bodyModel[300].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Box 314
		bodyModel[300].setRotationPoint(-31.95F, -0.25F, -0.88F);

		bodyModel[301].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, -0.05F, 0F, -0.05F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 315
		bodyModel[301].setRotationPoint(-31.95F, -1F, -0.88F);

		bodyModel[302].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, -0.05F); // Box 318
		bodyModel[302].setRotationPoint(-31.95F, -0.25F, -0.12F);

		bodyModel[303].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Box 319
		bodyModel[303].setRotationPoint(-31.95F, -1F, -0.12F);

		bodyModel[304].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 318
		bodyModel[304].setRotationPoint(9F, -20F, -4.25F);

		bodyModel[305].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 319
		bodyModel[305].setRotationPoint(9F, -21F, -5F);

		bodyModel[306].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 320
		bodyModel[306].setRotationPoint(2F, -21F, -5F);

		bodyModel[307].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 321
		bodyModel[307].setRotationPoint(2F, -30.25F, -5F);

		bodyModel[308].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 322
		bodyModel[308].setRotationPoint(13F, -21F, -5F);

		bodyModel[309].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 323
		bodyModel[309].setRotationPoint(14F, -21F, -5F);

		bodyModel[310].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 324
		bodyModel[310].setRotationPoint(13F, -30.25F, -5F);

		bodyModel[311].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 325
		bodyModel[311].setRotationPoint(14F, -21F, 5F);

		bodyModel[312].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 326
		bodyModel[312].setRotationPoint(9F, -20F, 3.25F);

		bodyModel[313].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 327
		bodyModel[313].setRotationPoint(13F, -30.25F, 5F);

		bodyModel[314].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 328
		bodyModel[314].setRotationPoint(12F, -33.75F, 5.25F);

		bodyModel[315].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 329
		bodyModel[315].setRotationPoint(10F, -32.75F, 4F);

		bodyModel[316].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 330
		bodyModel[316].setRotationPoint(10F, -33.75F, 5.25F);

		bodyModel[317].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 331
		bodyModel[317].setRotationPoint(10F, -33.75F, -4.75F);

		bodyModel[318].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 332
		bodyModel[318].setRotationPoint(12F, -33.75F, -4.75F);

		bodyModel[319].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 333
		bodyModel[319].setRotationPoint(10F, -32.75F, -5F);

		bodyModel[320].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 334
		bodyModel[320].setRotationPoint(2F, -21F, 5F);

		bodyModel[321].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 335
		bodyModel[321].setRotationPoint(2F, -30.25F, 5F);

		bodyModel[322].addShapeBox(0F, 0F, 0F, 5, 7, 0, 0F,0F, 0F, 0F, 0F, 0F, 3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 3F, 0F, 0F, -3F, 0F, 0F, 0F); // Box 336
		bodyModel[322].setRotationPoint(-33F, -15F, 10.5F);

		bodyModel[323].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0.25F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F); // Box 337
		bodyModel[323].setRotationPoint(-32.15F, -10F, 9F);
		bodyModel[323].rotateAngleY = -0.2268928F;

		bodyModel[324].addShapeBox(0F, 0F, 0F, 1, 4, 1, 0F,-1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -1F, 0F, -0.1F, -0.5F, -1F, -0.5F, 0F, 1F, 0F, 0F, 1F, -0.1F, -0.5F, -1F, -0.5F); // Box 341
		bodyModel[324].setRotationPoint(-29.45F, -11F, -7.4F);

		bodyModel[325].addShapeBox(0F, 0F, 0F, 1, 4, 1, 0F,-0.5F, -2.5F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -0.5F, -2.5F, -0.25F, -0.5F, 1.5F, 0F, 0F, 1.5F, -0.1F, 0F, 1.5F, 0F, -0.5F, 1.5F, -0.5F); // Box 343
		bodyModel[325].setRotationPoint(-29.5F, -10F, 6.4F);

		bodyModel[326].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 344
		bodyModel[326].setRotationPoint(-31.7F, 0F, 3.6F);

		bodyModel[327].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, 0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F); // Box 345
		bodyModel[327].setRotationPoint(-31.7F, 0F, 4.35F);

		bodyModel[328].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F); // Box 346
		bodyModel[328].setRotationPoint(-31.7F, 0.75F, 3.6F);

		bodyModel[329].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F); // Box 347
		bodyModel[329].setRotationPoint(-31.7F, 0.75F, 4.35F);

		bodyModel[330].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 356 lamp
		bodyModel[330].setRotationPoint(-31.9F, 0F, 3.6F);

		bodyModel[331].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Box 357 lamp
		bodyModel[331].setRotationPoint(-31.9F, 0.75F, 4.35F);

		bodyModel[332].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Box 358 lamp
		bodyModel[332].setRotationPoint(-31.9F, 0.75F, 3.6F);

		bodyModel[333].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Box 359 lamp
		bodyModel[333].setRotationPoint(-31.9F, 0F, 4.35F);

		bodyModel[334].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Box 359 lamp
		bodyModel[334].setRotationPoint(-31.9F, 0.75F, -4.55F);

		bodyModel[335].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Box 360 lamp
		bodyModel[335].setRotationPoint(-31.9F, 0.75F, -5.3F);

		bodyModel[336].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F); // Box 361
		bodyModel[336].setRotationPoint(-31.7F, 0.75F, -5.3F);

		bodyModel[337].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 362
		bodyModel[337].setRotationPoint(-31.7F, 0F, -5.3F);

		bodyModel[338].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 363 lamp
		bodyModel[338].setRotationPoint(-31.9F, 0F, -5.3F);

		bodyModel[339].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Box 364 lamp
		bodyModel[339].setRotationPoint(-31.9F, 0F, -4.55F);

		bodyModel[340].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, 0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F); // Box 365
		bodyModel[340].setRotationPoint(-31.7F, 0F, -4.55F);

		bodyModel[341].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F); // Box 366
		bodyModel[341].setRotationPoint(-31.7F, 0.75F, -4.55F);

		bodyModel[342].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,1.1F, -0.5F, 0.9F, 0.5F, -0.5F, -1F, 0.5F, -0.5F, 1F, 0F, -0.5F, 0.25F, 0.5F, 0F, -0.25F, 0.5F, 0F, -1F, 0.5F, 0F, 1F, 0F, 0F, 0.25F); // Box 359
		bodyModel[342].setRotationPoint(-30F, 5F, 5.75F);

		bodyModel[343].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, 0.2F, 0F, 0F, -0.55F, 0F, 0F, 0.55F, -0.5F, 0F, -0.2F, -0.4F, -0.2F, -0.1F, 0F, 0F, -0.6F, 0F, -0.2F, 0.3F, -0.5F, -0.2F, -0.5F); // Box 361
		bodyModel[343].setRotationPoint(-30.5F, 6F, 6.2F);

		bodyModel[344].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.5F, 0F, -0.2F, 0F, 0F, 0.55F, 0F, 0F, -0.55F, 0F, 0F, 0.2F, -0.5F, -0.2F, -0.5F, 0F, -0.2F, 0.3F, 0F, 0F, -0.6F, -0.4F, -0.2F, -0.1F); // Box 365
		bodyModel[344].setRotationPoint(-30.5F, 6F, -7.2F);

		bodyModel[345].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.5F, 0.25F, 0.5F, -0.5F, 1F, 0.5F, -0.5F, -1F, 1.1F, -0.5F, 0.9F, 0F, 0F, 0.25F, 0.5F, 0F, 1F, 0.5F, 0F, -1F, 0.5F, 0F, -0.25F); // Box 366
		bodyModel[345].setRotationPoint(-30F, 5F, -6.75F);

		bodyModel[346].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0.25F, 0.5F, 0F, 1F, 0.5F, 0F, -1F, 0.7F, 0F, 0F, 0F, -0.75F, 0.25F, 0.5F, -0.75F, 1F, 0.5F, -0.75F, -1F, 0.7F, -0.75F, 0F); // Box 367
		bodyModel[346].setRotationPoint(-30F, 4F, -6.76F);

		bodyModel[347].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, -1.25F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 1.25F, 0F, -0.75F, -1.25F, 0F, -0.75F, -0.5F, 0F, -0.75F, 0.5F, 0F, -0.75F, 1.25F); // Box 368
		bodyModel[347].setRotationPoint(-28.5F, 4F, -9.03F);

		bodyModel[348].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1.5F, 0F, -0.75F, -1.5F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 1.5F); // Box 369
		bodyModel[348].setRotationPoint(-26.5F, 4F, -10.01F);

		bodyModel[349].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.7F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.75F, 0.5F, 0F, -0.75F, 0.7F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F); // Box 370
		bodyModel[349].setRotationPoint(-21.5F, 4F, -9.51F);

		bodyModel[350].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 371
		bodyModel[350].setRotationPoint(-16.5F, 4F, -10.21F);

		bodyModel[351].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.7F, 0F, 0F, 0.5F, 0F, -1F, 0.5F, 0F, 1F, 0F, 0F, 0.25F, 0.7F, -0.75F, 0F, 0.5F, -0.75F, -1F, 0.5F, -0.75F, 1F, 0F, -0.75F, 0.25F); // Box 372
		bodyModel[351].setRotationPoint(-30F, 4F, 5.51F);

		bodyModel[352].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, 1.5F, 0F, 0F, 0.5F, 0F, 0F, -0.65F, 0F, 0F, -1.475F, 0F, -0.75F, 1.5F, 0F, -0.75F, 0.5F, 0F, -0.75F, -0.65F, 0F, -0.75F, -1.475F); // Box 373
		bodyModel[352].setRotationPoint(-28.5F, 4F, 7.99F);

		bodyModel[353].addShapeBox(0F, 0F, 0F, 21, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 376
		bodyModel[353].setRotationPoint(-16.51F, 4F, 9.21F);

		bodyModel[354].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 377
		bodyModel[354].setRotationPoint(14.49F, 4F, 9.21F);

		bodyModel[355].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 378
		bodyModel[355].setRotationPoint(26.49F, 4F, 8.71F);

		bodyModel[356].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 379
		bodyModel[356].setRotationPoint(26.49F, 4F, -9.69F);

		bodyModel[357].addShapeBox(-9F, -1F, -1F, 9, 1, 1, 0F,0F, 4F, 0F, 0F, -1F, 0F, 0F, -1F, 1F, 0F, 4F, 1F, 0F, -4F, 0F, 0F, 1F, 0F, 0F, 1F, 1F, 0F, -4F, 1F); // Box 58
		bodyModel[357].setRotationPoint(-10F, -21F, 0F);

		bodyModel[358].addShapeBox(1F, -1F, -3F, 19, 1, 12, 0F,1F, -2F, -1F, -9F, 4.5F, 1.5F, -9F, 4.5F, -4.5F, 1F, -2F, -7F, 1F, 2F, -1F, -9F, -4.5F, 1.5F, -9F, -4.5F, -4.5F, 1F, 2F, -7F); // Box 59
		bodyModel[358].setRotationPoint(-19.5F, -27F, 0F);

		bodyModel[359].addShapeBox(1F, -1F, -3F, 19, 1, 12, 0F,-9F, 4.5F, 1.5F, 1F, -2F, -1F, 1F, -2F, -7F, -9F, 4.5F, -4.5F, -9F, -4.5F, 1.5F, 1F, 2F, -1F, 1F, 2F, -7F, -9F, -4.5F, -4.5F); // Box 322
		bodyModel[359].setRotationPoint(-16.5F, -27F, 0F);

		bodyModel[360].addShapeBox(-9F, -1F, -1F, 9, 1, 1, 0F,0F, -1F, 0F, 0F, 4F, 0F, 0F, 4F, 1F, 0F, -1F, 1F, 0F, 1F, 0F, 0F, -4F, 0F, 0F, -4F, 1F, 0F, 1F, 1F); // Box 323
		bodyModel[360].setRotationPoint(4F, -21F, 0F);

		bodyModel[361].addShapeBox(0F, 0F, 0F, 1, 4, 1, 0F,-0.5F, -2.5F, -0.25F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.5F, -2.5F, -0.1F, -0.5F, 1.5F, -0.5F, 0F, 1.5F, 0F, 0F, 1.5F, -0.1F, -0.5F, 1.5F, 0F); // Box 379
		bodyModel[361].setRotationPoint(-29.5F, -10F, -7.4F);

		bodyModel[362].addShapeBox(0F, 0F, 0F, 48, 1, 5, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 377
		bodyModel[362].setRotationPoint(-21.5F, -20F, -8F);

		bodyModel[363].addShapeBox(0F, 0F, 0F, 48, 1, 5, 0F,0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 378
		bodyModel[363].setRotationPoint(-21.5F, -20F, 3F);

		bodyModel[364].addShapeBox(0F, 0F, 0F, 48, 1, 6, 0F,0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 379
		bodyModel[364].setRotationPoint(-21.5F, -20F, -3F);

		bodyModel[365].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0F, -1.5F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -1.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F); // Box 380
		bodyModel[365].setRotationPoint(-26.5F, -20F, -3F);

		bodyModel[366].addShapeBox(0F, 0F, 0F, 5, 1, 5, 0F,0F, -1.5F, 0F, 0F, -0.4F, 0F, 0F, -1F, 0F, 0F, -1.5F, -0.5F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, -0.5F); // Box 381
		bodyModel[366].setRotationPoint(-26.5F, -20F, 3F);

		bodyModel[367].addShapeBox(0F, 0F, 0F, 5, 1, 5, 0F,0F, -1.5F, -0.5F, 0F, -1F, 0F, 0F, -0.4F, 0F, 0F, -1.5F, 0F, 0F, 0.5F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F); // Box 382
		bodyModel[367].setRotationPoint(-26.5F, -20F, -8F);

		bodyModel[368].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.25F, 1.3F, -0.5F, -0.5F, 0.9F, 0F, -0.1F, 0.9F, -0.45F, -0.15F, 1.3F, -1.95F, -0.9F, 0.65F, -0.25F, -0.5F, 0.65F, 0F, -0.7F, 0.65F, -2.1F, -0.8F, 0.65F, -2.4F); // Box 383
		bodyModel[368].setRotationPoint(-30F, -11F, -7.75F);

		bodyModel[369].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 583
		bodyModel[369].setRotationPoint(12F, -33.75F, -6.75F);

		bodyModel[370].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 584
		bodyModel[370].setRotationPoint(10F, -33.75F, -6.75F);

		bodyModel[371].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, -0.375F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F); // Box 385
		bodyModel[371].setRotationPoint(-18.5F, -21F, 5.25F);

		bodyModel[372].addShapeBox(0F, 0F, 0F, 38, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 386
		bodyModel[372].setRotationPoint(-16.5F, -21F, 6.5F);

		bodyModel[373].addShapeBox(0F, 0F, 0F, 38, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 389
		bodyModel[373].setRotationPoint(-16.5F, -21F, -7.5F);

		bodyModel[374].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,-0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.375F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 390
		bodyModel[374].setRotationPoint(-18.5F, -21F, -7.25F);

		bodyModel[375].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, -0.5F, -0.25F, -0.5F, 0F, -0.375F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 389
		bodyModel[375].setRotationPoint(21.5F, -21F, -7.25F);

		bodyModel[376].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, 0F, -0.375F, 0F, -0.5F, -0.25F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F); // Box 390
		bodyModel[376].setRotationPoint(21.5F, -21F, 5.25F);

		bodyModel[377].addShapeBox(0F, 0F, 0F, 2, 4, 2, 0F,-1.3F, 0F, 1F, 0F, 0F, 1F, -0.25F, 0F, -0.65F, -1.3F, 0F, -3F, -1.1F, -0.8F, 1F, 0F, -0.67F, 1F, -0.43F, -0.9F, -0.6F, -1.1F, -0.8F, -3F); // Box 391
		bodyModel[377].setRotationPoint(-31.5F, -16F, 5.75F);

		bodyModel[378].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.4F, 0.25F, -0.5F, -0.5F, 0.25F, 0F, -0.5F, 0.25F, -2F, -0.2F, 0.25F, -1.95F, 0F, 0F, -0.75F, -0.5F, 0F, 0F, -0.5F, 0F, -2F, 1F, 0F, -0.35F); // Box 391
		bodyModel[378].setRotationPoint(-30F, -5.5F, -7.75F);

		bodyModel[379].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.9F, -0.4F, -0.25F, -0.5F, -0.4F, 0F, -1F, -0.4F, -2.3F, -0.8F, -0.4F, -2.45F, -0.4F, 1F, -0.5F, -0.5F, 1F, 0F, -1F, 1F, -2F, -0.2F, 1F, -1.95F); // Box 392
		bodyModel[379].setRotationPoint(-30F, -7.75F, -7.75F);

		bodyModel[380].addShapeBox(0F, 0F, 0F, 1, 3, 5, 0F,-1.4F, 0.35F, 2.2F, 0.6F, 0.35F, 2.2F, -0.85F, 0.35F, -0.25F, 0.15F, 0.35F, -0.25F, -0.2F, 0.1F, 1F, -0.6F, 0.1F, 1F, -1.8F, 0.1F, -0.25F, 1.3F, 0.1F, -0.25F); // Box 395
		bodyModel[380].setRotationPoint(-30.5F, -7F, -5F);

		bodyModel[381].addShapeBox(0F, 0F, 0F, 1, 5, 5, 0F,-1.5F, 0.25F, 1.35F, 0.8F, 0.25F, 0.6F, -0.1F, 0.5F, -0.25F, -0.7F, 0.5F, -0.25F, -0.4F, 0.85F, 2.2F, -0.5F, 0.85F, 2.2F, -1.75F, 0.85F, -0.25F, 1.15F, 0.85F, -0.25F); // Box 396
		bodyModel[381].setRotationPoint(-29.5F, -13.2F, -5F);

		bodyModel[382].addShapeBox(0F, 0F, 0F, 90, 6, 1, 0F,0F, 0F, 0F, -50F, 0F, 0F, -50F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, -3F, 0F, -50F, -3F, 0F, -50F, -3F, -0.9F, 0F, -3F, -0.9F); // Box 239
		bodyModel[382].setRotationPoint(-15F, -19.5F, -10.2F);

		bodyModel[383].addShapeBox(0F, 0F, 0F, 90, 6, 1, 0F,0F, 0F, 0F, -50F, 0F, 0F, -50F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, -3F, 0F, -50F, -3F, 0F, -50F, -3F, -0.9F, 0F, -3F, -0.9F); // Box 325
		bodyModel[383].setRotationPoint(-15F, -19.5F, 10F);

		bodyModel[384].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 393
		bodyModel[384].setRotationPoint(-31.9F, 0F, -2.25F);

		bodyModel[385].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F); // Box 394
		bodyModel[385].setRotationPoint(-31.9F, 0.75F, -2.25F);

		bodyModel[386].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F); // Box 395
		bodyModel[386].setRotationPoint(-31.9F, 0.75F, -1.5F);

		bodyModel[387].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, 0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F); // Box 396
		bodyModel[387].setRotationPoint(-31.9F, 0F, -1.5F);

		bodyModel[388].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 397
		bodyModel[388].setRotationPoint(-31.9F, 0F, 0.5F);

		bodyModel[389].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F); // Box 398
		bodyModel[389].setRotationPoint(-31.9F, 0.75F, 0.5F);

		bodyModel[390].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F); // Box 399
		bodyModel[390].setRotationPoint(-31.9F, 0.75F, 1.25F);

		bodyModel[391].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, 0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F); // Box 400
		bodyModel[391].setRotationPoint(-31.9F, 0F, 1.25F);

		bodyModel[392].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 401
		bodyModel[392].setRotationPoint(-31.8F, 0F, 0.5F);

		bodyModel[393].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -1.95F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 2F, 0F, -0.75F, -1.95F, 0F, -0.75F, -0.4F, 0F, -0.75F, 0F, 0F, -0.75F, 2F); // Box 615
		bodyModel[393].setRotationPoint(-26.5F, -16.1F, -10.51F);

		bodyModel[394].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 0.7F, 0F, 0F, 0.7F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.75F, 0.7F, 0F, -0.75F, 0.7F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F); // Box 617
		bodyModel[394].setRotationPoint(-21.5F, -16.1F, -9.41F);

		bodyModel[395].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 618
		bodyModel[395].setRotationPoint(-16.5F, -16.1F, -10.11F);

		bodyModel[396].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.3F, 0F, 1F, 0.5F, 0F, -1F, 0.5F, 0F, 1.05F, -0.15F, 0F, 0.35F, 0.3F, -0.75F, 1F, 0.5F, -0.75F, -1F, 0.5F, -0.75F, 1.05F, -0.15F, -0.75F, 0.35F); // Box 619
		bodyModel[396].setRotationPoint(-30F, -16.1F, 5.76F);

		bodyModel[397].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, -0.05F, 0F, 0F, -0.05F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, -0.05F, 0F, -0.75F, -0.05F); // Box 620
		bodyModel[397].setRotationPoint(-16.51F, -16.1F, 9.15F);

		bodyModel[398].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.15F, 0F, 0.35F, 0.5F, 0F, 1.05F, 0.5F, 0F, -1F, 0.3F, 0F, 1F, -0.15F, -0.75F, 0.35F, 0.5F, -0.75F, 1.05F, 0.5F, -0.75F, -1F, 0.3F, -0.75F, 1F); // Box 414
		bodyModel[398].setRotationPoint(-30F, -16.1F, -6.77F);

		bodyModel[399].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 2F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -1.95F, 0F, -0.75F, 2F, 0F, -0.75F, 0F, 0F, -0.75F, -0.3F, 0F, -0.75F, -1.95F); // Box 415
		bodyModel[399].setRotationPoint(-26.5F, -16.1F, 9.39F);

		bodyModel[400].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.6F, 0F, 0F, 0.15F, 0F, 0F, 0.15F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F, 0F, -0.75F, 0.15F, 0F, -0.75F, 0.15F); // Box 416
		bodyModel[400].setRotationPoint(-21.5F, -16.1F, 8.95F);

		bodyModel[401].addShapeBox(0F, 0F, 0F, 1, 1, 9, 0F,-0.1F, 0F, 0.25F, -0.6F, 0F, 1.25F, -0.6F, 0F, 1.25F, -0.1F, 0F, 0.25F, -0.1F, -0.75F, 0.25F, -0.6F, -0.75F, 1.25F, -0.6F, -0.75F, 1.25F, -0.1F, -0.75F, 0.25F); // Box 417
		bodyModel[401].setRotationPoint(-30.4F, -16.1F, -4.5F);

		bodyModel[402].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, -1.7F, 0F, 0F, -0.95F, 0F, 0F, 0.3F, 0F, 0F, 1.75F, 0F, -0.75F, -1.7F, 0F, -0.75F, -0.95F, 0F, -0.75F, 0.3F, 0F, -0.75F, 1.75F); // Box 411
		bodyModel[402].setRotationPoint(-28.5F, -16.1F, -9.51F);

		bodyModel[403].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, 0F, 1.75F, 0F, 0F, 0.3F, 0F, 0F, -1.05F, 0F, 0F, -1.7F, 0F, -0.75F, 1.75F, 0F, -0.75F, 0.3F, 0F, -0.75F, -1.05F, 0F, -0.75F, -1.7F); // Box 412
		bodyModel[403].setRotationPoint(-28.5F, -16.1F, 8.49F);

		bodyModel[404].addShapeBox(0F, 0F, 0F, 0, 7, 6, 0F,0F, -3F, -3F, 0F, -3F, -3F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 413
		bodyModel[404].setRotationPoint(-11F, -23F, -13.1F);

		bodyModel[405].addShapeBox(0F, 0F, 0F, 0, 7, 6, 0F,0F, -3F, -3F, 0F, -3F, -3F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 414
		bodyModel[405].setRotationPoint(-4F, -23F, -13.1F);

		bodyModel[406].addShapeBox(0F, 0F, 0F, 0, 7, 6, 0F,0F, -3F, -3F, 0F, -3F, -3F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 415
		bodyModel[406].setRotationPoint(6F, -23F, -13.1F);

		bodyModel[407].addShapeBox(0F, 0F, 0F, 0, 7, 6, 0F,0F, -3F, -3F, 0F, -3F, -3F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 416
		bodyModel[407].setRotationPoint(20F, -23F, -13.1F);

		bodyModel[408].addShapeBox(0F, 0F, 0F, 0, 7, 6, 0F,0F, -3F, -3F, 0F, -3F, -3F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 417
		bodyModel[408].setRotationPoint(20F, -23F, 4.1F);

		bodyModel[409].addShapeBox(0F, 0F, 0F, 0, 7, 6, 0F,0F, -3F, -3F, 0F, -3F, -3F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 418
		bodyModel[409].setRotationPoint(6F, -23F, 4.1F);

		bodyModel[410].addShapeBox(0F, 0F, 0F, 0, 7, 6, 0F,0F, -3F, -3F, 0F, -3F, -3F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 419
		bodyModel[410].setRotationPoint(-4F, -23F, 4.1F);

		bodyModel[411].addShapeBox(0F, 0F, 0F, 0, 7, 6, 0F,0F, -3F, -3F, 0F, -3F, -3F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 420
		bodyModel[411].setRotationPoint(-11F, -23F, 4.1F);

		bodyModel[412].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 318
		bodyModel[412].setRotationPoint(26.5F, -19.5F, 2.5F);

		bodyModel[413].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 319
		bodyModel[413].setRotationPoint(26.5F, -19.5F, -2.5F);

		bodyModel[414].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 320
		bodyModel[414].setRotationPoint(26.5F, -19.5F, -7.5F);

		bodyModel[415].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,-0.2F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.25F, 0.1F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F); // Box 649
		bodyModel[415].setRotationPoint(-31.2F, -0.05F, -5.6F);

		bodyModel[416].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0.25F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.2F, 0.1F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F); // Box 650
		bodyModel[416].setRotationPoint(-31.2F, -0.05F, 2.6F);

		bodyModel[417].addShapeBox(0F, 0F, 0F, 2, 4, 2, 0F,-1.3F, 0F, -3F, -0.25F, 0F, -0.65F, 0F, 0F, 1F, -1.3F, 0F, 1F, -1.1F, -0.8F, -3F, -0.4F, -0.9F, -0.6F, 0F, -0.67F, 1F, -1.1F, -0.8F, 1F); // Box 432
		bodyModel[417].setRotationPoint(-31.5F, -16F, -7.75F);

		bodyModel[418].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0.25F, 0F, 0.35F, 0F, 0F, 1F, 0F, 0F, 0F, -0.21F, 0F, 0F, 0.4F, 0.1F, 0.4F, 0F, -0.15F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 433
		bodyModel[418].setRotationPoint(-29.5F, -16F, -6.75F);

		bodyModel[419].addShapeBox(0F, 0F, 0F, 2, 2, 3, 0F,-0.1F, -0.8F, -0.6F, -0.5F, -0.55F, 0F, -0.1F, -1.25F, 0.25F, 0.3F, -0.875F, -0.5F, -0.25F, -0.6F, -0.5F, -0.5F, -0.2F, 0F, -0.1F, -0.2F, -0.45F, -0.15F, -0.6F, -1.95F); // Box 434
		bodyModel[419].setRotationPoint(-30F, -13.7F, -7.75F);

		bodyModel[420].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,-0.25F, -0.8F, -0.4F, 0.25F, -0.7F, -0.4F, 0F, -0.7F, -0.5F, 0F, -0.8F, -0.6F, -0.25F, 0F, -0.4F, 0.25F, 0F, -0.4F, 0F, 0F, -0.5F, 0F, 0F, -0.6F); // Box 639
		bodyModel[420].setRotationPoint(-31.75F, -0.85F, -5.4F);

		bodyModel[421].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.85F, -0.5F, 0F, -0.8F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.65F, -0.25F, 0F, -0.7F, -0.5F, 0F, -0.55F, -0.25F, 0F, -0.3F, -0.25F, 0F, -0.5F, -0.25F); // Box 640
		bodyModel[421].setRotationPoint(-31.5F, -0.7F, -5.75F);

		bodyModel[422].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.7F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.85F, -0.5F, 0F, -0.5F, -0.25F, 0F, -0.35F, -0.25F, 0F, -0.35F, -0.25F, 0F, -0.7F, -0.5F); // Box 641
		bodyModel[422].setRotationPoint(-31.75F, -0.7F, -3.25F);

		bodyModel[423].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,-0.25F, -1.25F, -0.5F, 0F, -1F, -0.5F, 0F, -1F, -0.5F, 0F, -1.25F, -0.5F, -0.25F, 0.75F, -0.5F, 0F, 0.75F, -0.5F, 0F, 0.75F, -0.5F, 0F, 0.75F, -0.5F); // Box 642
		bodyModel[423].setRotationPoint(-31.75F, -1.1F, -5.5F);

		bodyModel[424].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,-0.25F, 0.1F, -0.4F, 0.25F, 0F, -0.4F, 0F, 0F, -0.6F, 0F, 0.1F, -0.6F, -0.25F, -0.9F, -0.4F, 0.25F, -0.7F, -0.4F, 0F, -0.7F, -0.6F, 0F, -0.9F, -0.6F); // Box 643
		bodyModel[424].setRotationPoint(-31.75F, 1.7F, -5.4F);

		bodyModel[425].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, -0.8F, -0.4F, 0F, -0.9F, -0.4F, 0.25F, -0.9F, -0.5F, -0.25F, -0.8F, -0.6F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0.25F, 0F, -0.5F, -0.25F, 0F, -0.6F); // Box 644
		bodyModel[425].setRotationPoint(-31.75F, -0.85F, 2.6F);

		bodyModel[426].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, -1.25F, -0.5F, 0F, -1.2F, -0.5F, 0F, -1.2F, -0.5F, -0.25F, -1.25F, -0.5F, 0F, 0.75F, -0.5F, 0F, 0.75F, -0.5F, 0F, 0.75F, -0.5F, -0.25F, 0.75F, -0.5F); // Box 645
		bodyModel[426].setRotationPoint(-31.75F, -1.1F, 2.5F);

		bodyModel[427].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.65F, -0.25F, 0F, -0.7F, -0.25F, 0F, -0.9F, -0.25F, 0F, -0.85F, -0.5F, 0F, -0.5F, -0.25F, 0F, -0.35F, -0.25F, 0F, -0.35F, -0.25F, 0F, -0.7F, -0.5F); // Box 646
		bodyModel[427].setRotationPoint(-31.5F, -0.7F, 4.75F);

		bodyModel[428].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0.1F, -0.4F, 0F, 0F, -0.4F, 0.25F, 0F, -0.6F, -0.25F, 0.1F, -0.6F, 0F, -0.9F, -0.4F, 0F, -0.7F, -0.4F, 0.25F, -0.7F, -0.6F, -0.25F, -0.9F, -0.6F); // Box 647
		bodyModel[428].setRotationPoint(-31.75F, 1.7F, 2.6F);

		bodyModel[429].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.85F, -0.5F, 0F, -0.7F, -0.25F, 0F, -0.6F, -0.25F, 0F, -0.65F, -0.25F, 0F, -0.7F, -0.5F, 0F, -0.35F, -0.25F, 0F, -0.3F, -0.25F, 0F, -0.5F, -0.25F); // Box 648
		bodyModel[429].setRotationPoint(-31.75F, -0.7F, 2.25F);

		bodyModel[430].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.9F, 0.35F, -0.25F, -0.5F, 0.35F, 0F, -0.75F, 0.35F, -0.15F, -0.8F, 0.35F, -0.4F, -0.9F, 0.65F, -0.25F, -0.5F, 0.65F, 0F, -1F, 0.65F, -0.3F, -0.8F, 0.65F, -0.45F); // Box 431
		bodyModel[430].setRotationPoint(-30F, -9F, -7.75F);

		bodyModel[431].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.15F, 1.3F, -1.95F, -0.1F, 0.9F, -0.45F, -0.5F, 0.9F, 0F, -0.25F, 1.3F, -0.5F, -0.8F, 0.65F, -2.4F, -0.7F, 0.65F, -2.1F, -0.5F, 0.65F, 0F, -0.9F, 0.65F, -0.25F); // Box 432
		bodyModel[431].setRotationPoint(-30F, -11F, 4.75F);

		bodyModel[432].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.8F, 0.35F, -0.4F, -0.75F, 0.35F, -0.15F, -0.5F, 0.35F, 0F, -0.9F, 0.35F, -0.25F, -0.8F, 0.65F, -0.45F, -1F, 0.65F, -0.3F, -0.5F, 0.65F, 0F, -0.9F, 0.65F, -0.25F); // Box 433
		bodyModel[432].setRotationPoint(-30F, -9F, 6.75F);
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		for (int i = 0; i < 433; i++) {
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