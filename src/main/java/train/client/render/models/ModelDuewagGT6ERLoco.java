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
		bodyModel = new ModelRendererTurbo[426];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);


		flipAll();
	}

	private void initbodyModel_1()
	{
        bodyModel[0] = new ModelRendererTurbo(this, 0, 1, textureX, textureY); // Box 9
        bodyModel[1] = new ModelRendererTurbo(this, 41, 1, textureX, textureY); // Box 62
        bodyModel[2] = new ModelRendererTurbo(this, 161, 1, textureX, textureY); // Box 63
        bodyModel[3] = new ModelRendererTurbo(this, 24, 0, textureX, textureY); // Box 66
        bodyModel[4] = new ModelRendererTurbo(this, 216, 0, textureX, textureY); // Box 67
        bodyModel[5] = new ModelRendererTurbo(this, 1, 177, textureX, textureY); // Box 572
        bodyModel[6] = new ModelRendererTurbo(this, 36, 177, textureX, textureY); // Box 575
        bodyModel[7] = new ModelRendererTurbo(this, 36, 177, textureX, textureY); // Box 576
        bodyModel[8] = new ModelRendererTurbo(this, 25, 177, textureX, textureY); // Box 577
        bodyModel[9] = new ModelRendererTurbo(this, 425, 1, textureX, textureY); // Box 76
        bodyModel[10] = new ModelRendererTurbo(this, 433, 1, textureX, textureY); // Box 79
        bodyModel[11] = new ModelRendererTurbo(this, 441, 1, textureX, textureY); // Box 80
        bodyModel[12] = new ModelRendererTurbo(this, 449, 1, textureX, textureY); // Box 81
        bodyModel[13] = new ModelRendererTurbo(this, 249, 9, textureX, textureY); // Box 140
        bodyModel[14] = new ModelRendererTurbo(this, 233, 17, textureX, textureY); // Box 142
        bodyModel[15] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // Box 145
        bodyModel[16] = new ModelRendererTurbo(this, 345, 9, textureX, textureY); // Box 154
        bodyModel[17] = new ModelRendererTurbo(this, 377, 9, textureX, textureY); // Box 165
        bodyModel[18] = new ModelRendererTurbo(this, 219, 19, textureX, textureY); // Box 186
        bodyModel[19] = new ModelRendererTurbo(this, 9, 25, textureX, textureY); // Box 82
        bodyModel[20] = new ModelRendererTurbo(this, 457, 1, textureX, textureY); // Box 58
        bodyModel[21] = new ModelRendererTurbo(this, 17, 25, textureX, textureY); // Box 86
        bodyModel[22] = new ModelRendererTurbo(this, 41, 9, textureX, textureY); // Box 179
        bodyModel[23] = new ModelRendererTurbo(this, 25, 25, textureX, textureY); // Box 180
        bodyModel[24] = new ModelRendererTurbo(this, 33, 25, textureX, textureY); // Box 181
        bodyModel[25] = new ModelRendererTurbo(this, 377, 9, textureX, textureY); // Box 182
        bodyModel[26] = new ModelRendererTurbo(this, 457, 9, textureX, textureY); // Box 183
        bodyModel[27] = new ModelRendererTurbo(this, 41, 23, textureX, textureY); // Box 161
        bodyModel[28] = new ModelRendererTurbo(this, 57, 25, textureX, textureY); // Box 139
        bodyModel[29] = new ModelRendererTurbo(this, 233, 25, textureX, textureY); // Box 141
        bodyModel[30] = new ModelRendererTurbo(this, 169, 25, textureX, textureY); // Box 156
        bodyModel[31] = new ModelRendererTurbo(this, 425, 25, textureX, textureY); // Box 294
        bodyModel[32] = new ModelRendererTurbo(this, 65, 33, textureX, textureY); // Box 306
        bodyModel[33] = new ModelRendererTurbo(this, 201, 33, textureX, textureY); // Box 0
        bodyModel[34] = new ModelRendererTurbo(this, 439, 23, textureX, textureY); // Box 4
        bodyModel[35] = new ModelRendererTurbo(this, 481, 25, textureX, textureY); // Box 5
        bodyModel[36] = new ModelRendererTurbo(this, 265, 33, textureX, textureY); // Box 6
        bodyModel[37] = new ModelRendererTurbo(this, 217, 1, textureX, textureY); // Box 294
        bodyModel[38] = new ModelRendererTurbo(this, 281, 33, textureX, textureY,"lamp"); // Box 170 lamp
        bodyModel[39] = new ModelRendererTurbo(this, 379, 22, textureX, textureY); // Box 97
        bodyModel[40] = new ModelRendererTurbo(this, 337, 33, textureX, textureY); // Box 98
        bodyModel[41] = new ModelRendererTurbo(this, 369, 33, textureX, textureY); // Box 99
        bodyModel[42] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Box 104
        bodyModel[43] = new ModelRendererTurbo(this, 41, 41, textureX, textureY); // Box 107
        bodyModel[44] = new ModelRendererTurbo(this, 481, 33, textureX, textureY); // Box 110
        bodyModel[45] = new ModelRendererTurbo(this, 114, 41, textureX, textureY); // Box 112
        bodyModel[46] = new ModelRendererTurbo(this, 385, 41, textureX, textureY); // Box 113
        bodyModel[47] = new ModelRendererTurbo(this, 444, 49, textureX, textureY); // Box 117
        bodyModel[48] = new ModelRendererTurbo(this, 91, 57, textureX, textureY); // Box 118
        bodyModel[49] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 171
        bodyModel[50] = new ModelRendererTurbo(this, 489, 9, textureX, textureY); // Box 173
        bodyModel[51] = new ModelRendererTurbo(this, 41, 25, textureX, textureY); // Box 176
        bodyModel[52] = new ModelRendererTurbo(this, 289, 81, textureX, textureY); // Box 179
        bodyModel[53] = new ModelRendererTurbo(this, 393, 81, textureX, textureY); // Box 180
        bodyModel[54] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 182
        bodyModel[55] = new ModelRendererTurbo(this, 251, 87, textureX, textureY); // Box 184
        bodyModel[56] = new ModelRendererTurbo(this, 337, 33, textureX, textureY); // Box 185
        bodyModel[57] = new ModelRendererTurbo(this, 497, 33, textureX, textureY); // Box 186
        bodyModel[58] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 226
        bodyModel[59] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 227
        bodyModel[60] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 228
        bodyModel[61] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 195
        bodyModel[62] = new ModelRendererTurbo(this, 113, 89, textureX, textureY); // Box 197
        bodyModel[63] = new ModelRendererTurbo(this, 361, 41, textureX, textureY); // Box 207
        bodyModel[64] = new ModelRendererTurbo(this, 169, 89, textureX, textureY); // Box 208
        bodyModel[65] = new ModelRendererTurbo(this, 337, 57, textureX, textureY); // Box 209
        bodyModel[66] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Box 8
        bodyModel[67] = new ModelRendererTurbo(this, 25, 97, textureX, textureY); // Box 9
        bodyModel[68] = new ModelRendererTurbo(this, 345, 25, textureX, textureY); // Box 10
        bodyModel[69] = new ModelRendererTurbo(this, 441, 33, textureX, textureY); // Box 11
        bodyModel[70] = new ModelRendererTurbo(this, 481, 33, textureX, textureY); // Box 12
        bodyModel[71] = new ModelRendererTurbo(this, 113, 49, textureX, textureY); // Box 13
        bodyModel[72] = new ModelRendererTurbo(this, 297, 57, textureX, textureY); // Box 15
        bodyModel[73] = new ModelRendererTurbo(this, 417, 41, textureX, textureY); // Box 19
        bodyModel[74] = new ModelRendererTurbo(this, 97, 49, textureX, textureY); // Box 21
        bodyModel[75] = new ModelRendererTurbo(this, 321, 57, textureX, textureY); // Box 24
        bodyModel[76] = new ModelRendererTurbo(this, 423, 46, textureX, textureY); // Box 124
        bodyModel[77] = new ModelRendererTurbo(this, 137, 84, textureX, textureY); // Box 125
        bodyModel[78] = new ModelRendererTurbo(this, 137, 84, textureX, textureY); // Box 127
        bodyModel[79] = new ModelRendererTurbo(this, 313, 65, textureX, textureY); // Box 128
        bodyModel[80] = new ModelRendererTurbo(this, 225, 89, textureX, textureY); // Box 129
        bodyModel[81] = new ModelRendererTurbo(this, 353, 65, textureX, textureY); // Box 130
        bodyModel[82] = new ModelRendererTurbo(this, 17, 97, textureX, textureY); // Box 131
        bodyModel[83] = new ModelRendererTurbo(this, 423, 54, textureX, textureY); // Box 126
        bodyModel[84] = new ModelRendererTurbo(this, 49, 97, textureX, textureY); // Box 449
        bodyModel[85] = new ModelRendererTurbo(this, 209, 97, textureX, textureY); // Box 450
        bodyModel[86] = new ModelRendererTurbo(this, 225, 97, textureX, textureY); // Box 385
        bodyModel[87] = new ModelRendererTurbo(this, 329, 97, textureX, textureY); // Box 386
        bodyModel[88] = new ModelRendererTurbo(this, 361, 97, textureX, textureY); // Box 388
        bodyModel[89] = new ModelRendererTurbo(this, 33, 73, textureX, textureY); // Box 389
        bodyModel[90] = new ModelRendererTurbo(this, 361, 57, textureX, textureY); // Box 390
        bodyModel[91] = new ModelRendererTurbo(this, 345, 97, textureX, textureY); // Box 391
        bodyModel[92] = new ModelRendererTurbo(this, 225, 25, textureX, textureY); // Box 392
        bodyModel[93] = new ModelRendererTurbo(this, 81, 49, textureX, textureY); // Box 393
        bodyModel[94] = new ModelRendererTurbo(this, 121, 49, textureX, textureY); // Box 394
        bodyModel[95] = new ModelRendererTurbo(this, 345, 73, textureX, textureY); // Box 399
        bodyModel[96] = new ModelRendererTurbo(this, 465, 97, textureX, textureY); // Box 400
        bodyModel[97] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Box 401
        bodyModel[98] = new ModelRendererTurbo(this, 241, 65, textureX, textureY); // Box 405
        bodyModel[99] = new ModelRendererTurbo(this, 419, 57, textureX, textureY); // Box 406
        bodyModel[100] = new ModelRendererTurbo(this, 73, 73, textureX, textureY); // Box 407
        bodyModel[101] = new ModelRendererTurbo(this, 377, 97, textureX, textureY); // Box 408
        bodyModel[102] = new ModelRendererTurbo(this, 1, 73, textureX, textureY); // Box 409
        bodyModel[103] = new ModelRendererTurbo(this, 481, 97, textureX, textureY); // Box 410
        bodyModel[104] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // Box 414
        bodyModel[105] = new ModelRendererTurbo(this, 89, 105, textureX, textureY); // Box 419
        bodyModel[106] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 420
        bodyModel[107] = new ModelRendererTurbo(this, 137, 73, textureX, textureY); // Box 421
        bodyModel[108] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // Box 422
        bodyModel[109] = new ModelRendererTurbo(this, 145, 105, textureX, textureY); // Box 426
        bodyModel[110] = new ModelRendererTurbo(this, 161, 105, textureX, textureY); // Box 427
        bodyModel[111] = new ModelRendererTurbo(this, 177, 105, textureX, textureY); // Box 431
        bodyModel[112] = new ModelRendererTurbo(this, 73, 81, textureX, textureY); // Box 432
        bodyModel[113] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 433
        bodyModel[114] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 434
        bodyModel[115] = new ModelRendererTurbo(this, 257, 81, textureX, textureY); // Box 435
        bodyModel[116] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 436
        bodyModel[117] = new ModelRendererTurbo(this, 91, 107, textureX, textureY); // Box 24
        bodyModel[118] = new ModelRendererTurbo(this, 179, 107, textureX, textureY); // Box 458
        bodyModel[119] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 568
        bodyModel[120] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 569
        bodyModel[121] = new ModelRendererTurbo(this, 345, 33, textureX, textureY); // Box 279
        bodyModel[122] = new ModelRendererTurbo(this, 121, 41, textureX, textureY); // Box 280
        bodyModel[123] = new ModelRendererTurbo(this, 179, 107, textureX, textureY); // Box 284
        bodyModel[124] = new ModelRendererTurbo(this, 161, 105, textureX, textureY); // Box 285
        bodyModel[125] = new ModelRendererTurbo(this, 361, 97, textureX, textureY); // Box 286
        bodyModel[126] = new ModelRendererTurbo(this, 1, 73, textureX, textureY); // Box 287
        bodyModel[127] = new ModelRendererTurbo(this, 419, 57, textureX, textureY); // Box 288
        bodyModel[128] = new ModelRendererTurbo(this, 73, 73, textureX, textureY); // Box 289
        bodyModel[129] = new ModelRendererTurbo(this, 241, 65, textureX, textureY); // Box 290
        bodyModel[130] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Box 291
        bodyModel[131] = new ModelRendererTurbo(this, 345, 73, textureX, textureY); // Box 292
        bodyModel[132] = new ModelRendererTurbo(this, 329, 97, textureX, textureY); // Box 293
        bodyModel[133] = new ModelRendererTurbo(this, 377, 97, textureX, textureY); // Box 294
        bodyModel[134] = new ModelRendererTurbo(this, 465, 97, textureX, textureY); // Box 295
        bodyModel[135] = new ModelRendererTurbo(this, 145, 105, textureX, textureY); // Box 296
        bodyModel[136] = new ModelRendererTurbo(this, 33, 73, textureX, textureY); // Box 297
        bodyModel[137] = new ModelRendererTurbo(this, 121, 49, textureX, textureY); // Box 298
        bodyModel[138] = new ModelRendererTurbo(this, 361, 57, textureX, textureY); // Box 299
        bodyModel[139] = new ModelRendererTurbo(this, 345, 97, textureX, textureY); // Box 300
        bodyModel[140] = new ModelRendererTurbo(this, 81, 49, textureX, textureY); // Box 301
        bodyModel[141] = new ModelRendererTurbo(this, 225, 25, textureX, textureY); // Box 302
        bodyModel[142] = new ModelRendererTurbo(this, 91, 107, textureX, textureY); // Box 303
        bodyModel[143] = new ModelRendererTurbo(this, 225, 97, textureX, textureY); // Box 304
        bodyModel[144] = new ModelRendererTurbo(this, 89, 105, textureX, textureY); // Box 305
        bodyModel[145] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // Box 306
        bodyModel[146] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 307
        bodyModel[147] = new ModelRendererTurbo(this, 201, 81, textureX, textureY); // Box 308
        bodyModel[148] = new ModelRendererTurbo(this, 73, 81, textureX, textureY); // Box 309
        bodyModel[149] = new ModelRendererTurbo(this, 257, 81, textureX, textureY); // Box 310
        bodyModel[150] = new ModelRendererTurbo(this, 481, 97, textureX, textureY); // Box 311
        bodyModel[151] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Box 313
        bodyModel[152] = new ModelRendererTurbo(this, 177, 105, textureX, textureY); // Box 314
        bodyModel[153] = new ModelRendererTurbo(this, 505, 73, textureX, textureY); // Box 315
        bodyModel[154] = new ModelRendererTurbo(this, 273, 81, textureX, textureY); // Box 316
        bodyModel[155] = new ModelRendererTurbo(this, 137, 73, textureX, textureY); // Box 317
        bodyModel[156] = new ModelRendererTurbo(this, 73, 89, textureX, textureY); // Box 318
        bodyModel[157] = new ModelRendererTurbo(this, 361, 89, textureX, textureY); // Box 319
        bodyModel[158] = new ModelRendererTurbo(this, 113, 89, textureX, textureY); // Box 320
        bodyModel[159] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 322
        bodyModel[160] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 323
        bodyModel[161] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 324
        bodyModel[162] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 325
        bodyModel[163] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 326
        bodyModel[164] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 327
        bodyModel[165] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 328
        bodyModel[166] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 329
        bodyModel[167] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 330
        bodyModel[168] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 331
        bodyModel[169] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 332
        bodyModel[170] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 333
        bodyModel[171] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 334
        bodyModel[172] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 335
        bodyModel[173] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 336
        bodyModel[174] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 337
        bodyModel[175] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 338
        bodyModel[176] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 339
        bodyModel[177] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 340
        bodyModel[178] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 341
        bodyModel[179] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 342
        bodyModel[180] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 343
        bodyModel[181] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 344
        bodyModel[182] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 345
        bodyModel[183] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 346
        bodyModel[184] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 347
        bodyModel[185] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 348
        bodyModel[186] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 349
        bodyModel[187] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 376
        bodyModel[188] = new ModelRendererTurbo(this, 313, 57, textureX, textureY); // Box 379
        bodyModel[189] = new ModelRendererTurbo(this, 222, 91, textureX, textureY,"lamp"); // Lamp
        bodyModel[190] = new ModelRendererTurbo(this, 444, 62, textureX, textureY); // Box 333
        bodyModel[191] = new ModelRendererTurbo(this, 169, 38, textureX, textureY); // Box 336
        bodyModel[192] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // Lamp
        bodyModel[193] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // Lamp
        bodyModel[194] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // Lamp
        bodyModel[195] = new ModelRendererTurbo(this, 49, 1, textureX, textureY,"lamp"); // Lamp
        bodyModel[196] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // Lamp
        bodyModel[197] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // Lamp
        bodyModel[198] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // Lamp
        bodyModel[199] = new ModelRendererTurbo(this, 41, 1, textureX, textureY,"lamp"); // Lamp
        bodyModel[200] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // Lamp
        bodyModel[201] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // Lamp
        bodyModel[202] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // Lamp
        bodyModel[203] = new ModelRendererTurbo(this, 41, 5, textureX, textureY,"lamp"); // Lamp
        bodyModel[204] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // Lamp
        bodyModel[205] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // Lamp
        bodyModel[206] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // Lamp
        bodyModel[207] = new ModelRendererTurbo(this, 49, 5, textureX, textureY,"lamp"); // Lamp
        bodyModel[208] = new ModelRendererTurbo(this, 198, 65, textureX, textureY); // Box 361
        bodyModel[209] = new ModelRendererTurbo(this, 198, 76, textureX, textureY); // Box 362
        bodyModel[210] = new ModelRendererTurbo(this, 198, 52, textureX, textureY); // Box 363
        bodyModel[211] = new ModelRendererTurbo(this, 170, 76, textureX, textureY); // Box 364
        bodyModel[212] = new ModelRendererTurbo(this, 170, 65, textureX, textureY); // Box 365
        bodyModel[213] = new ModelRendererTurbo(this, 170, 52, textureX, textureY); // Box 366
        bodyModel[214] = new ModelRendererTurbo(this, 6, 54, textureX, textureY); // Box 367
        bodyModel[215] = new ModelRendererTurbo(this, 226, 67, textureX, textureY); // Box 368
        bodyModel[216] = new ModelRendererTurbo(this, 226, 78, textureX, textureY); // Box 369
        bodyModel[217] = new ModelRendererTurbo(this, 226, 54, textureX, textureY); // Box 370
        bodyModel[218] = new ModelRendererTurbo(this, 226, 51, textureX, textureY); // Box 371
        bodyModel[219] = new ModelRendererTurbo(this, 259, 51, textureX, textureY); // Box 372
        bodyModel[220] = new ModelRendererTurbo(this, 259, 54, textureX, textureY); // Box 373
        bodyModel[221] = new ModelRendererTurbo(this, 259, 67, textureX, textureY); // Box 374
        bodyModel[222] = new ModelRendererTurbo(this, 259, 78, textureX, textureY); // Box 375
        bodyModel[223] = new ModelRendererTurbo(this, 153, 46, textureX, textureY); // Box 376
        bodyModel[224] = new ModelRendererTurbo(this, 109, 60, textureX, textureY); // Box 377
        bodyModel[225] = new ModelRendererTurbo(this, 153, 41, textureX, textureY); // Box 378
        bodyModel[226] = new ModelRendererTurbo(this, 1, 140, textureX, textureY); // Box 13
        bodyModel[227] = new ModelRendererTurbo(this, 17, 140, textureX, textureY); // Box 16
        bodyModel[228] = new ModelRendererTurbo(this, 33, 140, textureX, textureY); // Box 17
        bodyModel[229] = new ModelRendererTurbo(this, 49, 140, textureX, textureY); // Box 18
        bodyModel[230] = new ModelRendererTurbo(this, 65, 140, textureX, textureY); // Box 19
        bodyModel[231] = new ModelRendererTurbo(this, 81, 140, textureX, textureY); // Box 20
        bodyModel[232] = new ModelRendererTurbo(this, 93, 140, textureX, textureY); // Box 21
        bodyModel[233] = new ModelRendererTurbo(this, 121, 140, textureX, textureY); // Box 22
        bodyModel[234] = new ModelRendererTurbo(this, 145, 140, textureX, textureY); // Box 24
        bodyModel[235] = new ModelRendererTurbo(this, 169, 140, textureX, textureY); // Box 25
        bodyModel[236] = new ModelRendererTurbo(this, 193, 140, textureX, textureY); // Box 26
        bodyModel[237] = new ModelRendererTurbo(this, 97, 140, textureX, textureY); // Box 27
        bodyModel[238] = new ModelRendererTurbo(this, 137, 140, textureX, textureY); // Box 29
        bodyModel[239] = new ModelRendererTurbo(this, 217, 140, textureX, textureY); // Box 30
        bodyModel[240] = new ModelRendererTurbo(this, 233, 140, textureX, textureY); // Box 31
        bodyModel[241] = new ModelRendererTurbo(this, 131, 151, textureX, textureY); // Box 40
        bodyModel[242] = new ModelRendererTurbo(this, 140, 151, textureX, textureY); // Box 41
        bodyModel[243] = new ModelRendererTurbo(this, 149, 151, textureX, textureY); // Box 42
        bodyModel[244] = new ModelRendererTurbo(this, 158, 151, textureX, textureY); // Box 43
        bodyModel[245] = new ModelRendererTurbo(this, 414, 65, textureX, textureY); // Box 61
        bodyModel[246] = new ModelRendererTurbo(this, 389, 65, textureX, textureY); // Box 88
        bodyModel[247] = new ModelRendererTurbo(this, 389, 58, textureX, textureY); // Box 172
        bodyModel[248] = new ModelRendererTurbo(this, 396, 58, textureX, textureY); // Box 176
        bodyModel[249] = new ModelRendererTurbo(this, 405, 58, textureX, textureY); // Box 404
        bodyModel[250] = new ModelRendererTurbo(this, 414, 58, textureX, textureY); // Box 405
        bodyModel[251] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 406
        bodyModel[252] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 407
        bodyModel[253] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 408
        bodyModel[254] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 409
        bodyModel[255] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 410
        bodyModel[256] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 411
        bodyModel[257] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 412
        bodyModel[258] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 413
        bodyModel[259] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 414
        bodyModel[260] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 415
        bodyModel[261] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 416
        bodyModel[262] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 417
        bodyModel[263] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 418
        bodyModel[264] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 419
        bodyModel[265] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 420
        bodyModel[266] = new ModelRendererTurbo(this, 180, 174, textureX, textureY); // Box 46
        bodyModel[267] = new ModelRendererTurbo(this, 113, 174, textureX, textureY); // Box 186
        bodyModel[268] = new ModelRendererTurbo(this, 104, 179, textureX, textureY); // Box 339
        bodyModel[269] = new ModelRendererTurbo(this, 95, 176, textureX, textureY); // Box 340
        bodyModel[270] = new ModelRendererTurbo(this, 148, 173, textureX, textureY); // Box 310
        bodyModel[271] = new ModelRendererTurbo(this, 141, 173, textureX, textureY); // Box 312
        bodyModel[272] = new ModelRendererTurbo(this, 476, 100, textureX, textureY); // Box 314
        bodyModel[273] = new ModelRendererTurbo(this, 372, 57, textureX, textureY); // Box 319
        bodyModel[274] = new ModelRendererTurbo(this, 372, 69, textureX, textureY); // Box 320
        bodyModel[275] = new ModelRendererTurbo(this, 1, 20, textureX, textureY); // Box 322
        bodyModel[276] = new ModelRendererTurbo(this, 60, 80, textureX, textureY); // Box 282
        bodyModel[277] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 331
        bodyModel[278] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 332
        bodyModel[279] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 333
        bodyModel[280] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 334
        bodyModel[281] = new ModelRendererTurbo(this, 297, 33, textureX, textureY); // Box 335
        bodyModel[282] = new ModelRendererTurbo(this, 273, 57, textureX, textureY); // Box 336
        bodyModel[283] = new ModelRendererTurbo(this, 169, 81, textureX, textureY); // Box 337
        bodyModel[284] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 338
        bodyModel[285] = new ModelRendererTurbo(this, 49, 44, textureX, textureY); // Box 342
        bodyModel[286] = new ModelRendererTurbo(this, 49, 124, textureX, textureY); // Box 170
        bodyModel[287] = new ModelRendererTurbo(this, 57, 124, textureX, textureY); // Box 171
        bodyModel[288] = new ModelRendererTurbo(this, 42, 126, textureX, textureY); // Box 437
        bodyModel[289] = new ModelRendererTurbo(this, 60, 126, textureX, textureY); // Box 438
        bodyModel[290] = new ModelRendererTurbo(this, 51, 127, textureX, textureY,"lamp"); // lamp
        bodyModel[291] = new ModelRendererTurbo(this, 29, 9, textureX, textureY); // Box 314
        bodyModel[292] = new ModelRendererTurbo(this, 29, 9, textureX, textureY); // Box 315
        bodyModel[293] = new ModelRendererTurbo(this, 29, 9, textureX, textureY); // Box 318
        bodyModel[294] = new ModelRendererTurbo(this, 29, 9, textureX, textureY); // Box 319
        bodyModel[295] = new ModelRendererTurbo(this, 252, 241, textureX, textureY); // Box 318
        bodyModel[296] = new ModelRendererTurbo(this, 49, 235, textureX, textureY); // Box 319
        bodyModel[297] = new ModelRendererTurbo(this, 121, 242, textureX, textureY); // Box 320
        bodyModel[298] = new ModelRendererTurbo(this, 121, 233, textureX, textureY); // Box 321
        bodyModel[299] = new ModelRendererTurbo(this, 65, 231, textureX, textureY); // Box 322
        bodyModel[300] = new ModelRendererTurbo(this, 121, 244, textureX, textureY); // Box 323
        bodyModel[301] = new ModelRendererTurbo(this, 121, 235, textureX, textureY); // Box 324
        bodyModel[302] = new ModelRendererTurbo(this, 121, 234, textureX, textureY); // Box 325
        bodyModel[303] = new ModelRendererTurbo(this, 97, 235, textureX, textureY); // Box 326
        bodyModel[304] = new ModelRendererTurbo(this, 121, 237, textureX, textureY); // Box 327
        bodyModel[305] = new ModelRendererTurbo(this, 256, 232, textureX, textureY); // Box 328
        bodyModel[306] = new ModelRendererTurbo(this, 104, 239, textureX, textureY); // Box 329
        bodyModel[307] = new ModelRendererTurbo(this, 113, 235, textureX, textureY); // Box 330
        bodyModel[308] = new ModelRendererTurbo(this, 1, 235, textureX, textureY); // Box 331
        bodyModel[309] = new ModelRendererTurbo(this, 25, 235, textureX, textureY); // Box 332
        bodyModel[310] = new ModelRendererTurbo(this, 104, 230, textureX, textureY); // Box 333
        bodyModel[311] = new ModelRendererTurbo(this, 121, 240, textureX, textureY); // Box 334
        bodyModel[312] = new ModelRendererTurbo(this, 121, 232, textureX, textureY); // Box 335
        bodyModel[313] = new ModelRendererTurbo(this, 3, 52, textureX, textureY); // Box 336
        bodyModel[314] = new ModelRendererTurbo(this, 6, 60, textureX, textureY); // Box 337
        bodyModel[315] = new ModelRendererTurbo(this, 2, 25, textureX, textureY); // Box 341
        bodyModel[316] = new ModelRendererTurbo(this, 2, 25, textureX, textureY); // Box 343
        bodyModel[317] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 344
        bodyModel[318] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 345
        bodyModel[319] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 346
        bodyModel[320] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 347
        bodyModel[321] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Lamp
        bodyModel[322] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Lamp
        bodyModel[323] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Lamp
        bodyModel[324] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Lamp
        bodyModel[325] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Lamp
        bodyModel[326] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Lamp
        bodyModel[327] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 361
        bodyModel[328] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 362
        bodyModel[329] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Lamp
        bodyModel[330] = new ModelRendererTurbo(this, 34, 20, textureX, textureY,"lamp"); // Lamp
        bodyModel[331] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 365
        bodyModel[332] = new ModelRendererTurbo(this, 29, 20, textureX, textureY); // Box 366
        bodyModel[333] = new ModelRendererTurbo(this, 141, 169, textureX, textureY); // Box 359
        bodyModel[334] = new ModelRendererTurbo(this, 140, 185, textureX, textureY); // Box 361
        bodyModel[335] = new ModelRendererTurbo(this, 147, 185, textureX, textureY); // Box 365
        bodyModel[336] = new ModelRendererTurbo(this, 148, 169, textureX, textureY); // Box 366
        bodyModel[337] = new ModelRendererTurbo(this, 283, 121, textureX, textureY); // Box 367
        bodyModel[338] = new ModelRendererTurbo(this, 291, 121, textureX, textureY); // Box 369
        bodyModel[339] = new ModelRendererTurbo(this, 303, 121, textureX, textureY); // Box 370
        bodyModel[340] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 371
        bodyModel[341] = new ModelRendererTurbo(this, 283, 121, textureX, textureY); // Box 372
        bodyModel[342] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 376
        bodyModel[343] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 377
        bodyModel[344] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 378
        bodyModel[345] = new ModelRendererTurbo(this, 315, 121, textureX, textureY); // Box 379
        bodyModel[346] = new ModelRendererTurbo(this, 173, 130, textureX, textureY); // Box 58
        bodyModel[347] = new ModelRendererTurbo(this, 185, 125, textureX, textureY); // Box 59
        bodyModel[348] = new ModelRendererTurbo(this, 118, 125, textureX, textureY); // Box 322
        bodyModel[349] = new ModelRendererTurbo(this, 173, 126, textureX, textureY); // Box 323
        bodyModel[350] = new ModelRendererTurbo(this, 2, 25, textureX, textureY); // Box 379
        bodyModel[351] = new ModelRendererTurbo(this, 1, 188, textureX, textureY); // Box 377
        bodyModel[352] = new ModelRendererTurbo(this, 1, 188, textureX, textureY); // Box 378
        bodyModel[353] = new ModelRendererTurbo(this, 1, 188, textureX, textureY); // Box 379
        bodyModel[354] = new ModelRendererTurbo(this, 19, 198, textureX, textureY); // Box 380
        bodyModel[355] = new ModelRendererTurbo(this, 2, 203, textureX, textureY); // Box 381
        bodyModel[356] = new ModelRendererTurbo(this, 2, 196, textureX, textureY); // Box 382
        bodyModel[357] = new ModelRendererTurbo(this, 211, 173, textureX, textureY); // Box 383
        bodyModel[358] = new ModelRendererTurbo(this, 481, 80, textureX, textureY); // Box 583
        bodyModel[359] = new ModelRendererTurbo(this, 481, 80, textureX, textureY); // Box 584
        bodyModel[360] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 385
        bodyModel[361] = new ModelRendererTurbo(this, 329, 1, textureX, textureY); // Box 386
        bodyModel[362] = new ModelRendererTurbo(this, 233, 1, textureX, textureY); // Box 389
        bodyModel[363] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 390
        bodyModel[364] = new ModelRendererTurbo(this, 25, 47, textureX, textureY); // Box 389
        bodyModel[365] = new ModelRendererTurbo(this, 1, 47, textureX, textureY); // Box 390
        bodyModel[366] = new ModelRendererTurbo(this, 199, 173, textureX, textureY); // Box 391
        bodyModel[367] = new ModelRendererTurbo(this, 199, 166, textureX, textureY); // Box 392
        bodyModel[368] = new ModelRendererTurbo(this, 99, 130, textureX, textureY); // Box 395
        bodyModel[369] = new ModelRendererTurbo(this, 109, 121, textureX, textureY); // Box 396
        bodyModel[370] = new ModelRendererTurbo(this, 137, 247, textureX, textureY); // Box 239
        bodyModel[371] = new ModelRendererTurbo(this, 321, 247, textureX, textureY); // Box 325
        bodyModel[372] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 393
        bodyModel[373] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 394
        bodyModel[374] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 395
        bodyModel[375] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 396
        bodyModel[376] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 397
        bodyModel[377] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 398
        bodyModel[378] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 399
        bodyModel[379] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 400
        bodyModel[380] = new ModelRendererTurbo(this, 21, 20, textureX, textureY); // Box 401
        bodyModel[381] = new ModelRendererTurbo(this, 275, 125, textureX, textureY); // Box 615
        bodyModel[382] = new ModelRendererTurbo(this, 288, 125, textureX, textureY); // Box 617
        bodyModel[383] = new ModelRendererTurbo(this, 267, 128, textureX, textureY); // Box 618
        bodyModel[384] = new ModelRendererTurbo(this, 270, 125, textureX, textureY); // Box 619
        bodyModel[385] = new ModelRendererTurbo(this, 301, 125, textureX, textureY); // Box 620
        bodyModel[386] = new ModelRendererTurbo(this, 270, 131, textureX, textureY); // Box 414
        bodyModel[387] = new ModelRendererTurbo(this, 275, 131, textureX, textureY); // Box 415
        bodyModel[388] = new ModelRendererTurbo(this, 288, 131, textureX, textureY); // Box 416
        bodyModel[389] = new ModelRendererTurbo(this, 253, 124, textureX, textureY); // Box 417
        bodyModel[390] = new ModelRendererTurbo(this, 2, 164, textureX, textureY); // Box 318
        bodyModel[391] = new ModelRendererTurbo(this, 2, 164, textureX, textureY); // Box 319
        bodyModel[392] = new ModelRendererTurbo(this, 2, 164, textureX, textureY); // Box 320
        bodyModel[393] = new ModelRendererTurbo(this, 0, 88, textureX, textureY); // Box 649
        bodyModel[394] = new ModelRendererTurbo(this, 0, 94, textureX, textureY); // Box 650
        bodyModel[395] = new ModelRendererTurbo(this, 495, 101, textureX, textureY); // Box 432
        bodyModel[396] = new ModelRendererTurbo(this, 10, 4, textureX, textureY); // Box 433
        bodyModel[397] = new ModelRendererTurbo(this, 212, 166, textureX, textureY); // Box 434
        bodyModel[398] = new ModelRendererTurbo(this, 10, 85, textureX, textureY); // Box 639
        bodyModel[399] = new ModelRendererTurbo(this, 16, 83, textureX, textureY); // Box 640
        bodyModel[400] = new ModelRendererTurbo(this, 16, 83, textureX, textureY); // Box 641
        bodyModel[401] = new ModelRendererTurbo(this, 4, 82, textureX, textureY,"lamp"); // Lamp
        bodyModel[402] = new ModelRendererTurbo(this, 10, 85, textureX, textureY); // Box 643
        bodyModel[403] = new ModelRendererTurbo(this, 10, 85, textureX, textureY); // Box 644
        bodyModel[404] = new ModelRendererTurbo(this, 4, 82, textureX, textureY,"lamp"); // Lamp
        bodyModel[405] = new ModelRendererTurbo(this, 16, 83, textureX, textureY); // Box 646
        bodyModel[406] = new ModelRendererTurbo(this, 10, 85, textureX, textureY); // Box 647
        bodyModel[407] = new ModelRendererTurbo(this, 16, 83, textureX, textureY); // Box 648
        bodyModel[408] = new ModelRendererTurbo(this, 213, 179, textureX, textureY); // Box 431
        bodyModel[409] = new ModelRendererTurbo(this, 105, 169, textureX, textureY); // Box 432
        bodyModel[410] = new ModelRendererTurbo(this, 105, 176, textureX, textureY); // Box 433
        bodyModel[411] = new ModelRendererTurbo(this, 12, 76, textureX, textureY); // Box 651
        bodyModel[412] = new ModelRendererTurbo(this, 15, 75, textureX, textureY); // Box 652
        bodyModel[413] = new ModelRendererTurbo(this, 3, 76, textureX, textureY); // Box 653
        bodyModel[414] = new ModelRendererTurbo(this, 3, 76, textureX, textureY); // Box 436
        bodyModel[415] = new ModelRendererTurbo(this, 15, 75, textureX, textureY); // Box 437
        bodyModel[416] = new ModelRendererTurbo(this, 12, 76, textureX, textureY); // Box 438
        bodyModel[417] = new ModelRendererTurbo(this, 116, 164, textureX, textureY); // Box 424
        bodyModel[418] = new ModelRendererTurbo(this, 495, 115, textureX, textureY); // Box 425
        bodyModel[419] = new ModelRendererTurbo(this, 10, 12, textureX, textureY); // Box 426
        bodyModel[420] = new ModelRendererTurbo(this, 99, 128, textureX, textureY); // Box 422
        bodyModel[421] = new ModelRendererTurbo(this, 71, 129, textureX, textureY); // Box 423
        bodyModel[422] = new ModelRendererTurbo(this, 71, 127, textureX, textureY); // Box 424
        bodyModel[423] = new ModelRendererTurbo(this, 88, 121, textureX, textureY); // Box 425
        bodyModel[424] = new ModelRendererTurbo(this, 1, 36, textureX, textureY); // Box 425
        bodyModel[425] = new ModelRendererTurbo(this, 1, 31, textureX, textureY); // Box 426

        bodyModel[0].addShapeBox(0F, 0F, 0F, 4, 1, 17, 0F,1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 1.5F, 0F, 0.5F, 1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 1.5F, 0F, 0.5F); // Box 9
        bodyModel[0].setRotationPoint(-21F, 4F, -9F);

        bodyModel[1].addShapeBox(0F, 0F, 0F, 43, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 62
        bodyModel[1].setRotationPoint(-16.5F, -19F, -8F);

        bodyModel[2].addShapeBox(0F, 0F, 0F, 5, 2, 19, 0F,0F, 0F, -2.05F, 0F, 0F, -0.45F, 0F, 0F, -0.2F, 0F, 0F, -2.09F, 0F, 0F, -1.25F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -1.25F); // Box 63
        bodyModel[2].setRotationPoint(-26.5F, -18F, -9.5F);

        bodyModel[3].addShapeBox(0F, 0F, 0F, 1, 2, 14, 0F,0F, -0.5F, -1.05F, 0F, 0.5F, 0.25F, 0F, 0.5F, 0.25F, 0F, -0.5F, -1.05F, 0.3F, 0F, 0.55F, 0F, 0F, 1.25F, 0F, 0F, 1.25F, 0.3F, 0F, 0.45F); // Box 66
        bodyModel[3].setRotationPoint(-27.5F, -18F, -7F);

        bodyModel[4].addShapeBox(0F, 0F, 0F, 1, 1, 12, 0F,-0.35F, -0.3F, -1.55F, 0F, 0.5F, 0F, 0F, 0.5F, -0.05F, -0.35F, -0.3F, -1.55F, -0.3F, 0F, -0.55F, -0.3F, 0F, 1.55F, -0.3F, 0F, 1.45F, -0.3F, 0F, -0.55F); // Box 67
        bodyModel[4].setRotationPoint(-28.5F, -17F, -6F);

        bodyModel[5].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0.1F, -0.5F, 0F, -0.4F); // Box 572
        bodyModel[5].setRotationPoint(-0.5F, -21F, 6.2F);

        bodyModel[6].addShapeBox(0F, 0F, 0F, 20, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 575
        bodyModel[6].setRotationPoint(1.5F, -21F, -8.45F);

        bodyModel[7].addShapeBox(0F, 0F, 0F, 20, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.15F, 0F, 0F, -0.15F); // Box 576
        bodyModel[7].setRotationPoint(1.5F, -21F, 7.45F);

        bodyModel[8].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,-0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F); // Box 577
        bodyModel[8].setRotationPoint(-0.5F, -21F, -8.2F);

        bodyModel[9].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 76
        bodyModel[9].setRotationPoint(4.5F, -14F, 9.5F);

        bodyModel[10].addShapeBox(-2F, 0F, -0.5F, 2, 20, 1, 0F,0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F); // Box 79
        bodyModel[10].setRotationPoint(14.5F, -14F, 9.5F);

        bodyModel[11].addShapeBox(-2F, 0F, -0.5F, 2, 20, 1, 0F,0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F); // Box 80
        bodyModel[11].setRotationPoint(12F, -14F, 9.5F);

        bodyModel[12].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0.5F, 1F, -0.25F, 0F, 1F, -0.25F, 0F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0.5F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 81
        bodyModel[12].setRotationPoint(7F, -14F, 9.5F);

        bodyModel[13].addShapeBox(0F, 0F, 0F, 43, 2, 2, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 140
        bodyModel[13].setRotationPoint(-16.5F, -18F, 8F);

        bodyModel[14].addShapeBox(0F, 0F, 0F, 43, 2, 2, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F); // Box 142
        bodyModel[14].setRotationPoint(-16.5F, -18F, -10F);

        bodyModel[15].addShapeBox(0F, 0F, 0F, 5, 1, 17, 0F,0F, -0.5F, -1.25F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.5F, -1.25F, 0F, 0F, -1.05F, 0F, 0F, 0.55F, 0F, 0F, 0.8F, 0F, 0F, -1.09F); // Box 145
        bodyModel[15].setRotationPoint(-26.5F, -19F, -8.5F);

        bodyModel[16].addShapeBox(0F, 0F, 0F, 5, 2, 19, 0F,0F, 0F, 0.05F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -0.7F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 154
        bodyModel[16].setRotationPoint(-21.5F, -18F, -9F);

        bodyModel[17].addShapeBox(0F, 0F, 0F, 5, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1.05F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0.3F); // Box 165
        bodyModel[17].setRotationPoint(-21.5F, -19F, -8F);

        bodyModel[18].addShapeBox(0F, 0F, 0F, 1, 1, 11, 0F,-0.5F, 0F, -2F, 0F, 0F, 0F, 0F, 0F, -1F, -0.5F, 0F, -3F, -0.5F, 0.75F, -2F, 0F, 0.75F, 0F, 0F, 0.75F, -1F, -0.5F, 0.75F, -3F); // Box 186
        bodyModel[18].setRotationPoint(-30.7F, 4F, -5F);

        bodyModel[19].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.5F, 0.5F, 1F, -1.25F, 0.5F, 1F, 0.875F, 0F, 1F, 0F, 0F, 0F, -0.5F, 0.5F, 0F, -1.25F, 0.5F, 0F, 0.875F, 0F, 0F, 0F); // Box 82
        bodyModel[19].setRotationPoint(-26.5F, -14F, 7.5F);

        bodyModel[20].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -1F); // Box 58
        bodyModel[20].setRotationPoint(-26.5F, -16F, 6F);

        bodyModel[21].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.125F, 0.5F, 1F, -1F, 0.5F, 1F, 0.5F, 0F, 1F, -0.375F, 0F, 0F, -0.125F, 0.5F, 0F, -1F, 0.5F, 0F, 0.5F, 0F, 0F, -0.375F); // Box 86
        bodyModel[21].setRotationPoint(-24F, -14F, 8.75F);

        bodyModel[22].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 179
        bodyModel[22].setRotationPoint(-21.5F, -16F, 6.5F);

        bodyModel[23].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -1F, 0.5F, 1F, -1F, 0.5F, 1F, 0.5F, 0F, 1F, 0.5F, 0F, 0F, -1F, 0.5F, 0F, -1F, 0.5F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 180
        bodyModel[23].setRotationPoint(-21.5F, -14F, 8.75F);

        bodyModel[24].addShapeBox(0F, 0F, -0.5F, 2, 20, 1, 0F,0F, 1F, -0.75F, 0.5F, 1F, -0.75F, 0.5F, 1F, 0.25F, 0F, 1F, 0.25F, 0F, 0F, -0.75F, 0.5F, 0F, -0.75F, 0.5F, 0F, 0.25F, 0F, 0F, 0.25F); // Box 181
        bodyModel[24].setRotationPoint(-19F, -14F, 9F);

        bodyModel[25].addShapeBox(0F, 1F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 182
        bodyModel[25].setRotationPoint(-21.5F, 5F, 6.5F);

        bodyModel[26].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -0.7F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, -0.7F); // Box 183
        bodyModel[26].setRotationPoint(-26.5F, 6F, 6F);

        bodyModel[27].addShapeBox(0F, 0F, 0F, 1, 3, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0.5F, -0.06F, 0F, 0F, 0.35F, 0F, 0F, 0.35F, 0.5F, 0.5F, -0.06F, 0.5F); // Box 161
        bodyModel[27].setRotationPoint(-27.5F, -16.2F, -5.25F);

        bodyModel[28].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 139
        bodyModel[28].setRotationPoint(-16.5F, -16F, 9F);

        bodyModel[29].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 141
        bodyModel[29].setRotationPoint(-16.5F, -16F, -10F);

        bodyModel[30].addShapeBox(0F, 0F, 0F, 21, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 156
        bodyModel[30].setRotationPoint(-16.5F, -14.5F, 9F);

        bodyModel[31].addShapeBox(0F, 0F, 0F, 10, 3, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 294
        bodyModel[31].setRotationPoint(4.5F, -18F, 6F);

        bodyModel[32].addShapeBox(0F, 0F, 0F, 37, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F); // Box 306
        bodyModel[32].setRotationPoint(-13.5F, -18F, -1F);

        bodyModel[33].addShapeBox(0F, 0F, 0F, 21, 1, 17, 0F,0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 1F, 0F, 0F, 1F); // Box 0
        bodyModel[33].setRotationPoint(-17.5F, 4F, -9F);

        bodyModel[34].addShapeBox(0F, 0F, 0F, 10, 3, 15, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 4
        bodyModel[34].setRotationPoint(4.5F, 4F, -8F);

        bodyModel[35].addBox(0F, 0F, 0F, 10, 1, 3, 0F); // Box 5
        bodyModel[35].setRotationPoint(4.5F, 6F, 7.1F);

        bodyModel[36].addBox(0F, 0F, 0F, 10, 1, 1, 0F); // Box 6
        bodyModel[36].setRotationPoint(4.5F, 4F, 7F);

        bodyModel[37].addShapeBox(0F, 0F, 0F, 1, 3, 3, 0F,0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 2F, -1F, 0F, 2F, -1F, 0F, 0F, 0F, 0F); // Box 294
        bodyModel[37].setRotationPoint(-28.19F, -19F, -1.5F);

        bodyModel[38].addShapeBox(0F, 0F, 0F, 1, 9, 9, 0F,0F, 0F, -6F, 0F, -0.3F, -6F, 0F, -0.3F, 0F, 0F, 0F, 0F, 0F, -6F, -6F, 0F, -6F, -6F, 0F, -6F, 0F, 0F, -6F, 0F); // Box 170 lamp
        bodyModel[38].setRotationPoint(-28.2F, -19F, -7.5F);

        bodyModel[39].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.2F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 97
        bodyModel[39].setRotationPoint(-21.5F, -16F, -9.5F);

        bodyModel[40].addBox(0F, 0F, 0F, 1, 2, 14, 0F); // Box 98
        bodyModel[40].setRotationPoint(-23.5F, 5F, -8.5F);

        bodyModel[41].addShapeBox(0F, 0F, 0F, 5, 21, 1, 0F,0F, 1F, -1.75F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 1.5F, 0F, 0F, -1.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1.5F); // Box 99
        bodyModel[41].setRotationPoint(-26.5F, -15F, -10F);

        bodyModel[42].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -1.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1.75F, 0F, -0.2F, -1.95F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 1.75F); // Box 104
        bodyModel[42].setRotationPoint(-26.5F, 6F, -10F);

        bodyModel[43].addShapeBox(0F, 0F, 0F, 21, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 107
        bodyModel[43].setRotationPoint(-16.5F, 6F, 9.1F);

        bodyModel[44].addShapeBox(0F, 0F, 0F, 2, 1, 11, 0F,-1.13F, -0.5F, -0.5F, -0.45F, -0.5F, -0.5F, -0.45F, -0.5F, -0.5F, -1.13F, -0.5F, -0.5F, -1.1F, -0.1F, -0.5F, -0.5F, -0.05F, -0.5F, -0.5F, -0.05F, -0.5F, -1.1F, -0.1F, -0.5F); // Box 110
        bodyModel[44].setRotationPoint(-29.5F, -14.2F, -5.5F);

        bodyModel[45].addShapeBox(0F, 0F, 0F, 4, 1, 13, 0F,0F, 0F, 1F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 0F, 0F); // Box 112
        bodyModel[45].setRotationPoint(-26.5F, 4F, -6.5F);

        bodyModel[46].addShapeBox(0F, 0F, 0F, 3, 2, 14, 0F,3F, 0F, 2.25F, 0F, 0F, 2.5F, 0F, 0F, 0F, 3F, 0F, -0.5F, 3F, 0F, 2.25F, 0F, 0F, 2.5F, 0F, 0F, 0F, 3F, 0F, -0.5F); // Box 113
        bodyModel[46].setRotationPoint(-19.5F, 5F, -6.5F);

        bodyModel[47].addShapeBox(0F, 0F, 0F, 31, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 117
        bodyModel[47].setRotationPoint(-16.5F, -15F, -10F);

        bodyModel[48].addShapeBox(0F, 0F, 0F, 31, 1, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 118
        bodyModel[48].setRotationPoint(-16.5F, 6F, -10.1F);

        bodyModel[49].addShapeBox(0F, 0F, 0F, 3, 1, 10, 0F,-1.25F, 0F, -0.25F, 0F, 0F, 2F, 0F, 0F, 3F, -1.25F, 0F, 1F, -1.25F, -0.25F, -0.25F, 0F, 0F, 2F, 0F, 0F, 3F, -1.25F, -0.25F, 1F); // Box 171
        bodyModel[49].setRotationPoint(-29.5F, 4F, -5.5F);

        bodyModel[50].addShapeBox(0F, 0F, 0F, 4, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -0.5F); // Box 173
        bodyModel[50].setRotationPoint(-26.5F, 5F, 5.5F);

        bodyModel[51].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.3F, 0F, 0F, -0.2F); // Box 176
        bodyModel[51].setRotationPoint(-27.5F, 4.9F, 6F);

        bodyModel[52].addShapeBox(0F, 0F, 0F, 1, 2, 17, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 179
        bodyModel[52].setRotationPoint(14.5F, 5F, -8.5F);

        bodyModel[53].addShapeBox(0F, 0F, 0F, 1, 2, 17, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 180
        bodyModel[53].setRotationPoint(3.5F, 5F, -8.5F);

        bodyModel[54].addShapeBox(0F, 0F, 0F, 7, 7, 5, 0F,0F, 0F, -4F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -4F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 182
        bodyModel[54].setRotationPoint(-28.5F, -3F, -10F);

        bodyModel[55].addShapeBox(0F, 0F, 0F, 2, 2, 9, 0F,0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 184
        bodyModel[55].setRotationPoint(-28.5F, -4F, -5F);

        bodyModel[56].addShapeBox(0F, 0F, 0F, 1, 8, 3, 0F,-1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F); // Box 185
        bodyModel[56].setRotationPoint(-26.5F, -3.5F, 4F);

        bodyModel[57].addShapeBox(0F, 0F, 0F, 2, 7, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 186
        bodyModel[57].setRotationPoint(-28.5F, -3F, 4F);

        bodyModel[58].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 226
        bodyModel[58].setRotationPoint(2.5F, -5F, -8.5F);

        bodyModel[59].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 227
        bodyModel[59].setRotationPoint(-0.5F, 0F, -8.5F);

        bodyModel[60].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 228
        bodyModel[60].setRotationPoint(1.75F, 0.5F, -6F);

        bodyModel[61].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 195
        bodyModel[61].setRotationPoint(3.25F, -18F, -5.5F);

        bodyModel[62].addBox(0F, 0F, 0F, 0, 22, 4, 0F); // Box 197
        bodyModel[62].setRotationPoint(14.5F, -17F, 4.5F);

        bodyModel[63].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 207
        bodyModel[63].setRotationPoint(-25.25F, 0.5F, -1F);

        bodyModel[64].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 208
        bodyModel[64].setRotationPoint(-26.5F, 0F, -2.5F);

        bodyModel[65].addShapeBox(0F, 0F, 0F, 1, 4, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 209
        bodyModel[65].setRotationPoint(-23.5F, -4F, -2.5F);

        bodyModel[66].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 8
        bodyModel[66].setRotationPoint(-9F, -33.75F, -4.75F);

        bodyModel[67].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 9
        bodyModel[67].setRotationPoint(-7F, -33.75F, -4.75F);

        bodyModel[68].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 10
        bodyModel[68].setRotationPoint(-9F, -32.75F, -5F);

        bodyModel[69].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 11
        bodyModel[69].setRotationPoint(-9F, -32.75F, 4F);

        bodyModel[70].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 12
        bodyModel[70].setRotationPoint(-7F, -33.75F, -6.75F);

        bodyModel[71].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 13
        bodyModel[71].setRotationPoint(-9F, -33.75F, 5.25F);

        bodyModel[72].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 15
        bodyModel[72].setRotationPoint(-9F, -33.75F, -6.75F);

        bodyModel[73].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 19
        bodyModel[73].setRotationPoint(-10F, -20F, -4.25F);

        bodyModel[74].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 21
        bodyModel[74].setRotationPoint(-10F, -20F, 3.25F);

        bodyModel[75].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 24
        bodyModel[75].setRotationPoint(-7F, -33.75F, 5.25F);

        bodyModel[76].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 124
        bodyModel[76].setRotationPoint(-17F, -21F, -5F);

        bodyModel[77].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 125
        bodyModel[77].setRotationPoint(-17F, -30.25F, -5F);

        bodyModel[78].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 127
        bodyModel[78].setRotationPoint(-17F, -30.25F, 5F);

        bodyModel[79].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 128
        bodyModel[79].setRotationPoint(-5F, -21F, 5F);

        bodyModel[80].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 129
        bodyModel[80].setRotationPoint(-6F, -30.25F, 5F);

        bodyModel[81].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 130
        bodyModel[81].setRotationPoint(-5F, -21F, -5F);

        bodyModel[82].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 131
        bodyModel[82].setRotationPoint(-6F, -30.25F, -5F);

        bodyModel[83].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 126
        bodyModel[83].setRotationPoint(-17F, -21F, 5F);

        bodyModel[84].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 449
        bodyModel[84].setRotationPoint(-10F, -21F, -5F);

        bodyModel[85].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 450
        bodyModel[85].setRotationPoint(-6F, -21F, -5F);

        bodyModel[86].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 385
        bodyModel[86].setRotationPoint(-5F, 6F, -3F);

        bodyModel[87].addBox(0F, 0F, 0F, 8, 3, 4, 0F); // Box 386
        bodyModel[87].setRotationPoint(-13F, 6F, -2F);

        bodyModel[88].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 388
        bodyModel[88].setRotationPoint(-16F, 6F, -3F);

        bodyModel[89].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 389
        bodyModel[89].setRotationPoint(-6.15F, 5F, -5F);

        bodyModel[90].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 390
        bodyModel[90].setRotationPoint(-4.65F, 6.5F, -6F);

        bodyModel[91].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 391
        bodyModel[91].setRotationPoint(-4.15F, 7F, -5F);

        bodyModel[92].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 392
        bodyModel[92].setRotationPoint(-2.95F, 6.15F, -6.5F);

        bodyModel[93].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 393
        bodyModel[93].setRotationPoint(-5.65F, 8.75F, -6.5F);

        bodyModel[94].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 394
        bodyModel[94].setRotationPoint(-6.35F, 6.15F, -6.5F);

        bodyModel[95].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 399
        bodyModel[95].setRotationPoint(-11.5F, 6.75F, -5F);

        bodyModel[96].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 400
        bodyModel[96].setRotationPoint(-11F, 6F, -4F);

        bodyModel[97].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 401
        bodyModel[97].setRotationPoint(-12.5F, 7.5F, -6.5F);

        bodyModel[98].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 405
        bodyModel[98].setRotationPoint(-13.8F, 6.15F, -6.5F);

        bodyModel[99].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 406
        bodyModel[99].setRotationPoint(-16.5F, 8.75F, -6.5F);

        bodyModel[100].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 407
        bodyModel[100].setRotationPoint(-15.5F, 6.5F, -6F);

        bodyModel[101].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 408
        bodyModel[101].setRotationPoint(-15F, 7F, -5F);

        bodyModel[102].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 409
        bodyModel[102].setRotationPoint(-17.2F, 6.15F, -6.5F);

        bodyModel[103].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 410
        bodyModel[103].setRotationPoint(-12.5F, 7.5F, 5.5F);

        bodyModel[104].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 414
        bodyModel[104].setRotationPoint(-11.5F, 6.75F, 4F);

        bodyModel[105].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 419
        bodyModel[105].setRotationPoint(-6.15F, 5F, 5F);

        bodyModel[106].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 420
        bodyModel[106].setRotationPoint(-5.65F, 8.75F, 5F);

        bodyModel[107].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 421
        bodyModel[107].setRotationPoint(-4.65F, 6.5F, 4.5F);

        bodyModel[108].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 422
        bodyModel[108].setRotationPoint(-2.95F, 6.15F, 5F);

        bodyModel[109].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 426
        bodyModel[109].setRotationPoint(-8F, 6F, -4F);

        bodyModel[110].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 427
        bodyModel[110].setRotationPoint(-17F, 5F, -5F);

        bodyModel[111].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 431
        bodyModel[111].setRotationPoint(-17F, 5F, 5F);

        bodyModel[112].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 432
        bodyModel[112].setRotationPoint(-15.5F, 6.5F, 4.5F);

        bodyModel[113].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 433
        bodyModel[113].setRotationPoint(-16.5F, 8.75F, 5F);

        bodyModel[114].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 434
        bodyModel[114].setRotationPoint(-13.8F, 6.15F, 5F);

        bodyModel[115].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 435
        bodyModel[115].setRotationPoint(-17.2F, 6.15F, 5F);

        bodyModel[116].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 436
        bodyModel[116].setRotationPoint(-6.35F, 6.15F, 5F);

        bodyModel[117].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 24
        bodyModel[117].setRotationPoint(-1F, 8.25F, -6F);

        bodyModel[118].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 458
        bodyModel[118].setRotationPoint(-18F, 8.25F, -6F);

        bodyModel[119].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, -0.5F, 0F, -0.4F, 0F, -0.3F, 0F, 0F, -0.3F, 0F); // Box 568
        bodyModel[119].setRotationPoint(21.5F, -21F, -8.2F);

        bodyModel[120].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, -0.5F, 0F, -0.4F, 0F, 0F, 0.1F); // Box 569
        bodyModel[120].setRotationPoint(21.5F, -21F, 6.2F);

        bodyModel[121].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, -1.5F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 2.5F, 0F, 0.75F, -1.5F, 0F, 0.75F, 0.25F, 0F, 0.75F, 0F, 0F, 0.75F, 2.5F); // Box 279
        bodyModel[121].setRotationPoint(-29.7F, 4F, -6.5F);

        bodyModel[122].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 2.5F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -1.5F, 0F, 0.75F, 2.5F, 0F, 0.75F, 0F, 0F, 0.75F, 0.25F, 0F, 0.75F, -1.5F); // Box 280
        bodyModel[122].setRotationPoint(-29.7F, 4F, 5.5F);

        bodyModel[123].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 284
        bodyModel[123].setRotationPoint(19.5F, 8.25F, -6F);

        bodyModel[124].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 285
        bodyModel[124].setRotationPoint(20.5F, 5F, -5F);

        bodyModel[125].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 286
        bodyModel[125].setRotationPoint(21.5F, 6F, -3F);

        bodyModel[126].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 287
        bodyModel[126].setRotationPoint(20.3F, 6.15F, -6.5F);

        bodyModel[127].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 288
        bodyModel[127].setRotationPoint(21F, 8.75F, -6.5F);

        bodyModel[128].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 289
        bodyModel[128].setRotationPoint(22F, 6.5F, -6F);

        bodyModel[129].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 290
        bodyModel[129].setRotationPoint(23.7F, 6.15F, -6.5F);

        bodyModel[130].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 291
        bodyModel[130].setRotationPoint(24.9F, 7.5F, -6.5F);

        bodyModel[131].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 292
        bodyModel[131].setRotationPoint(26F, 6.75F, -5F);

        bodyModel[132].addBox(0F, 0F, 0F, 8, 3, 4, 0F); // Box 293
        bodyModel[132].setRotationPoint(24.5F, 6F, -2F);

        bodyModel[133].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 294
        bodyModel[133].setRotationPoint(22.5F, 7F, -5F);

        bodyModel[134].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 295
        bodyModel[134].setRotationPoint(26.5F, 6F, -4F);

        bodyModel[135].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 296
        bodyModel[135].setRotationPoint(29.5F, 6F, -4F);

        bodyModel[136].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 297
        bodyModel[136].setRotationPoint(31.35F, 5F, -5F);

        bodyModel[137].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 298
        bodyModel[137].setRotationPoint(31.15F, 6.15F, -6.5F);

        bodyModel[138].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 299
        bodyModel[138].setRotationPoint(32.85F, 6.5F, -6F);

        bodyModel[139].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 300
        bodyModel[139].setRotationPoint(33.35F, 7F, -5F);

        bodyModel[140].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 301
        bodyModel[140].setRotationPoint(31.85F, 8.75F, -6.5F);

        bodyModel[141].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 302
        bodyModel[141].setRotationPoint(34.55F, 6.15F, -6.5F);

        bodyModel[142].addBox(0F, 0F, 0F, 1, 1, 12, 0F); // Box 303
        bodyModel[142].setRotationPoint(36.5F, 8.25F, -6F);

        bodyModel[143].addBox(0F, 0F, 0F, 3, 3, 6, 0F); // Box 304
        bodyModel[143].setRotationPoint(32.5F, 6F, -3F);

        bodyModel[144].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 305
        bodyModel[144].setRotationPoint(31.35F, 5F, 5F);

        bodyModel[145].addBox(0F, 0F, 0F, 5, 3, 1, 0F); // Box 306
        bodyModel[145].setRotationPoint(26F, 6.75F, 4F);

        bodyModel[146].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 307
        bodyModel[146].setRotationPoint(21F, 8.75F, 5F);

        bodyModel[147].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 308
        bodyModel[147].setRotationPoint(23.7F, 6.15F, 5F);

        bodyModel[148].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 309
        bodyModel[148].setRotationPoint(22F, 6.5F, 4.5F);

        bodyModel[149].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 310
        bodyModel[149].setRotationPoint(20.3F, 6.15F, 5F);

        bodyModel[150].addShapeBox(0F, 0F, 0F, 7, 1, 1, 0F,0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F); // Box 311
        bodyModel[150].setRotationPoint(24.9F, 7.5F, 5.5F);

        bodyModel[151].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 313
        bodyModel[151].setRotationPoint(31.85F, 8.75F, 5F);

        bodyModel[152].addBox(0F, 0F, 0F, 5, 5, 0, 0F); // Box 314
        bodyModel[152].setRotationPoint(20.5F, 5F, 5F);

        bodyModel[153].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 315
        bodyModel[153].setRotationPoint(34.55F, 6.15F, 5F);

        bodyModel[154].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.5F, 0.2F, 0F, -0.5F, 0.2F, 0F, 0F, -0.8F, 0F, 0F); // Box 316
        bodyModel[154].setRotationPoint(31.15F, 6.15F, 5F);

        bodyModel[155].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, -0.5F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F); // Box 317
        bodyModel[155].setRotationPoint(32.85F, 6.5F, 4.5F);

        bodyModel[156].addBox(0F, 0F, 0F, 0, 22, 4, 0F); // Box 318
        bodyModel[156].setRotationPoint(-16.4F, -17F, -8.5F);

        bodyModel[157].addBox(0F, 0F, 0F, 19, 0, 3, 0F); // Box 319
        bodyModel[157].setRotationPoint(-15F, -14.5F, -8.5F);

        bodyModel[158].addBox(0F, 0F, 0F, 19, 0, 3, 0F); // Box 320
        bodyModel[158].setRotationPoint(-16.1F, -14.5F, 6F);

        bodyModel[159].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 322
        bodyModel[159].setRotationPoint(-4.25F, 0.5F, -6F);

        bodyModel[160].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 323
        bodyModel[160].setRotationPoint(-3.5F, -5F, -8.5F);

        bodyModel[161].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 324
        bodyModel[161].setRotationPoint(-6.5F, 0F, -8.5F);

        bodyModel[162].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 325
        bodyModel[162].setRotationPoint(-2.75F, -18F, -5.5F);

        bodyModel[163].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 326
        bodyModel[163].setRotationPoint(-9.25F, 0.5F, -6F);

        bodyModel[164].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 327
        bodyModel[164].setRotationPoint(-8.5F, -5F, -8.5F);

        bodyModel[165].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 328
        bodyModel[165].setRotationPoint(-11.5F, 0F, -8.5F);

        bodyModel[166].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 329
        bodyModel[166].setRotationPoint(-7.75F, -18F, -5.5F);

        bodyModel[167].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 330
        bodyModel[167].setRotationPoint(-13.25F, 0.5F, -6F);

        bodyModel[168].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 331
        bodyModel[168].setRotationPoint(-12.5F, -5F, -8.5F);

        bodyModel[169].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 332
        bodyModel[169].setRotationPoint(-15.5F, 0F, -8.5F);

        bodyModel[170].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 333
        bodyModel[170].setRotationPoint(-11.75F, -18F, -5.5F);

        bodyModel[171].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 334
        bodyModel[171].setRotationPoint(-13.25F, 0.5F, 5F);

        bodyModel[172].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 335
        bodyModel[172].setRotationPoint(-12.5F, -5F, 4.5F);

        bodyModel[173].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 336
        bodyModel[173].setRotationPoint(-15.5F, 0F, 4.5F);

        bodyModel[174].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 337
        bodyModel[174].setRotationPoint(-11.75F, -18F, 4.5F);

        bodyModel[175].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 338
        bodyModel[175].setRotationPoint(-9.25F, 0.5F, 5F);

        bodyModel[176].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 339
        bodyModel[176].setRotationPoint(-11.5F, 0F, 4.5F);

        bodyModel[177].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 340
        bodyModel[177].setRotationPoint(-8.5F, -5F, 4.5F);

        bodyModel[178].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 341
        bodyModel[178].setRotationPoint(-6.5F, 0F, 4.5F);

        bodyModel[179].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 342
        bodyModel[179].setRotationPoint(-3.5F, -5F, 4.5F);

        bodyModel[180].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 343
        bodyModel[180].setRotationPoint(-4.25F, 0.5F, 5F);

        bodyModel[181].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 344
        bodyModel[181].setRotationPoint(-0.5F, 0F, 4.5F);

        bodyModel[182].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 345
        bodyModel[182].setRotationPoint(1.75F, 0.5F, 5F);

        bodyModel[183].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 346
        bodyModel[183].setRotationPoint(2.5F, -5F, 4.5F);

        bodyModel[184].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 347
        bodyModel[184].setRotationPoint(-7.75F, -18F, 4.5F);

        bodyModel[185].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 348
        bodyModel[185].setRotationPoint(-2.75F, -18F, 4.5F);

        bodyModel[186].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 349
        bodyModel[186].setRotationPoint(3.25F, -18F, 4.5F);

        bodyModel[187].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 376
        bodyModel[187].setRotationPoint(19.25F, -18F, 4F);

        bodyModel[188].addShapeBox(0F, 0F, 0F, 1, 2, 4, 0F,0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 379
        bodyModel[188].setRotationPoint(-23F, -6F, -2.5F);

        bodyModel[189].addShapeBox(0F, 0F, 0F, 1, 6, 23, 0F,0F, 0F, 0.15F, 0F, 0F, 0F, 0F, 0F, -13.5F, 0F, 0F, -13.15F, 0F, -4.2F, 0.15F, 0F, -4.2F, 0.05F, 0F, -4.2F, -13.5F, 0F, -4.2F, -13.15F); // Lamp
        bodyModel[189].setRotationPoint(-28.1F, -15.5F, -4.75F);

        bodyModel[190].addShapeBox(0F, 0F, 0F, 31, 9, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 333
        bodyModel[190].setRotationPoint(-16.5F, -3.5F, -10.1F);

        bodyModel[191].addShapeBox(0F, 0F, 0F, 21, 9, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 336
        bodyModel[191].setRotationPoint(-16.5F, -3F, 9.1F);

        bodyModel[192].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Lamp
        bodyModel[192].setRotationPoint(-30.1F, -1F, -0.88F);

        bodyModel[193].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F); // Lamp
        bodyModel[193].setRotationPoint(-30.1F, -1F, -0.129999999999999F);

        bodyModel[194].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, 0.1F, -0.125F, 0F, -0.15F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F); // Lamp
        bodyModel[194].setRotationPoint(-30.1F, -0.25F, -0.129999999999999F);

        bodyModel[195].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.1F, 0F, -0.125F, 0.1F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.15F, 0F, -0.15F, -0.15F, 0F, 0.1F, -0.125F, 0F, 0.1F, -0.125F); // Lamp
        bodyModel[195].setRotationPoint(-30.1F, -0.25F, -0.88F);

        bodyModel[196].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.4F, 0F, -0.125F, 0.4F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.025F, 0.15F, 0F, -0.025F, 0.15F, 0F, 0.4F, -0.125F, 0F, 0.4F, -0.125F); // Lamp
        bodyModel[196].setRotationPoint(-30.2F, 1.25F, -0.88F);

        bodyModel[197].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.4F, 0F, -0.125F, 0.4F, 0F, 0.4F, -0.125F, 0F, 0.15F, -0.125F, 0F, -0.025F, 0.15F, 0F, -0.025F, 0.15F); // Lamp
        bodyModel[197].setRotationPoint(-30.2F, 1.25F, -0.129999999999999F);

        bodyModel[198].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.4F, -0.125F, 0F, 0.4F, -0.125F, 0F, -0.025F, 0.15F, 0F, -0.025F, 0.15F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.4F, 0F, -0.125F, 0.4F); // Lamp
        bodyModel[198].setRotationPoint(-30.2F, 0.5F, -0.129999999999999F);

        bodyModel[199].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.025F, 0.15F, 0F, -0.025F, 0.15F, 0F, 0.4F, -0.125F, 0F, 0.4F, -0.125F, 0F, -0.125F, 0.4F, 0F, -0.125F, 0.4F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Lamp
        bodyModel[199].setRotationPoint(-30.2F, 0.5F, -0.88F);

        bodyModel[200].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Lamp
        bodyModel[200].setRotationPoint(-30.1F, 0.75F, 0.5F);

        bodyModel[201].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Lamp
        bodyModel[201].setRotationPoint(-30.1F, 0.75F, 1.25F);

        bodyModel[202].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Lamp
        bodyModel[202].setRotationPoint(-30.1F, 0F, 1.25F);

        bodyModel[203].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Lamp
        bodyModel[203].setRotationPoint(-30.1F, 0F, 0.5F);

        bodyModel[204].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Lamp
        bodyModel[204].setRotationPoint(-30.1F, 0.75F, -2.25F);

        bodyModel[205].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Lamp
        bodyModel[205].setRotationPoint(-30.1F, 0.75F, -1.5F);

        bodyModel[206].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Lamp
        bodyModel[206].setRotationPoint(-30.1F, 0F, -1.5F);

        bodyModel[207].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Lamp
        bodyModel[207].setRotationPoint(-30.1F, 0F, -2.25F);

        bodyModel[208].addShapeBox(0F, 0F, 0F, 12, 9, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 361
        bodyModel[208].setRotationPoint(14.5F, -3.5F, -10.1F);

        bodyModel[209].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 362
        bodyModel[209].setRotationPoint(14.5F, 6F, -10.1F);

        bodyModel[210].addShapeBox(0F, 0F, 0F, 12, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 363
        bodyModel[210].setRotationPoint(14.5F, -15F, -10F);

        bodyModel[211].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 364
        bodyModel[211].setRotationPoint(14.5F, 6F, 9.1F);

        bodyModel[212].addShapeBox(0F, 0F, 0F, 12, 9, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 365
        bodyModel[212].setRotationPoint(14.5F, -3F, 9.1F);

        bodyModel[213].addShapeBox(0F, 0F, 0F, 12, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 366
        bodyModel[213].setRotationPoint(14.5F, -14.5F, 9F);

        bodyModel[214].addShapeBox(0F, 0F, 0F, 12, 1, 17, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F); // Box 367
        bodyModel[214].setRotationPoint(14.5F, 4F, -9F);

        bodyModel[215].addShapeBox(0F, 0F, 0F, 4, 9, 1, 0F,0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F); // Box 368
        bodyModel[215].setRotationPoint(26.5F, -3F, 8.6F);

        bodyModel[216].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 369
        bodyModel[216].setRotationPoint(26.5F, 6F, 8.6F);

        bodyModel[217].addShapeBox(0F, 0F, 0F, 4, 11, 1, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F); // Box 370
        bodyModel[217].setRotationPoint(26.5F, -14.5F, 8.5F);

        bodyModel[218].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 371
        bodyModel[218].setRotationPoint(26.5F, -16F, 8.5F);

        bodyModel[219].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 372
        bodyModel[219].setRotationPoint(26.5F, -16F, -9.5F);

        bodyModel[220].addShapeBox(0F, 0F, 0F, 4, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0F, 0F, 0.5F, 0F); // Box 373
        bodyModel[220].setRotationPoint(26.5F, -15F, -9.5F);

        bodyModel[221].addShapeBox(0F, 0F, 0F, 4, 9, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F, 0F, 0.5F, 0.1F); // Box 374
        bodyModel[221].setRotationPoint(26.5F, -3.5F, -9.6F);

        bodyModel[222].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 375
        bodyModel[222].setRotationPoint(26.5F, 6F, -9.6F);

        bodyModel[223].addShapeBox(0F, 0F, 0F, 4, 2, 2, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F); // Box 376
        bodyModel[223].setRotationPoint(26.5F, -18F, -9.5F);

        bodyModel[224].addShapeBox(0F, 0F, 0F, 4, 1, 16, 0F,0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -1F, 0F, -0.5F, -1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 377
        bodyModel[224].setRotationPoint(26.5F, -19F, -7.5F);

        bodyModel[225].addShapeBox(0F, 0F, 0F, 4, 2, 2, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 378
        bodyModel[225].setRotationPoint(26.5F, -18F, 7.5F);

        bodyModel[226].addShapeBox(0F, 0F, 0F, 3, 2, 3, 0F,0F, -1F, 1F, 0F, -1F, 1F, 0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 3F, 0F, -1F, 3F); // Box 13
        bodyModel[226].setRotationPoint(27F, -18F, -6F);

        bodyModel[227].addShapeBox(0F, 0F, 0F, 3, 4, 1, 0F,0F, -2F, -1F, 0F, -2F, -1F, 0F, -3F, 1F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 16
        bodyModel[227].setRotationPoint(27F, -19F, -8F);

        bodyModel[228].addShapeBox(0F, 0F, 0F, 3, 4, 1, 0F,0F, -3F, 1F, 0F, -3F, 1F, 0F, -2F, -1F, 0F, -2F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 17
        bodyModel[228].setRotationPoint(27F, -19F, 7F);

        bodyModel[229].addShapeBox(0F, 0F, 0F, 3, 2, 3, 0F,0F, 0F, 3F, 0F, 0F, 3F, 0F, -1F, 1F, 0F, -1F, 1F, 0F, -1F, 3F, 0F, -1F, 3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 18
        bodyModel[229].setRotationPoint(27F, -18F, 3F);

        bodyModel[230].addBox(0F, 0F, 0F, 3, 19, 1, 0F); // Box 19
        bodyModel[230].setRotationPoint(27F, -15F, 7F);

        bodyModel[231].addBox(0F, 0F, 0F, 3, 19, 1, 0F); // Box 20
        bodyModel[231].setRotationPoint(27F, -15F, -8F);

        bodyModel[232].addBox(0F, 0F, 0F, 4, 1, 14, 0F); // Box 21
        bodyModel[232].setRotationPoint(26.5F, 3.99F, -7F);

        bodyModel[233].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F); // Box 22
        bodyModel[233].setRotationPoint(30.5F, 3.99F, -7F);

        bodyModel[234].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F); // Box 24
        bodyModel[234].setRotationPoint(30.5F, 3.99F, 0F);

        bodyModel[235].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F); // Box 25
        bodyModel[235].setRotationPoint(23.5F, 3.99F, 0F);

        bodyModel[236].addShapeBox(0F, 0F, 0F, 3, 1, 7, 0F,0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F); // Box 26
        bodyModel[236].setRotationPoint(23.5F, 3.99F, -7F);

        bodyModel[237].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 27
        bodyModel[237].setRotationPoint(27F, -4F, 5.5F);

        bodyModel[238].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 29
        bodyModel[238].setRotationPoint(27F, -4F, -6.5F);

        bodyModel[239].addShapeBox(0F, 0F, 0F, 3, 7, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 30
        bodyModel[239].setRotationPoint(27F, -3F, -6.5F);

        bodyModel[240].addShapeBox(0F, 0F, 0F, 3, 7, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 31
        bodyModel[240].setRotationPoint(27F, -3F, 5.5F);

        bodyModel[241].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F); // Box 40
        bodyModel[241].setRotationPoint(25F, -3F, -6.5F);

        bodyModel[242].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F); // Box 41
        bodyModel[242].setRotationPoint(30F, -3F, -6F);

        bodyModel[243].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -1F); // Box 42
        bodyModel[243].setRotationPoint(25F, -3F, 5.5F);

        bodyModel[244].addShapeBox(0F, 0F, 0F, 2, 7, 1, 0F,0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0.5F); // Box 43
        bodyModel[244].setRotationPoint(30F, -3F, 5F);

        bodyModel[245].addBox(0F, 0F, 0F, 1, 19, 2, 0F); // Box 61
        bodyModel[245].setRotationPoint(25.5F, -15F, 7F);

        bodyModel[246].addBox(0F, 0F, 0F, 1, 19, 2, 0F); // Box 88
        bodyModel[246].setRotationPoint(25.5F, -15F, -9F);

        bodyModel[247].addShapeBox(0F, 0F, 0F, 1, 4, 2, 0F,0F, -3F, 1F, 0F, -3F, 1F, 0F, -1F, -0.5F, 0F, -1F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 172
        bodyModel[247].setRotationPoint(25.5F, -19F, 7F);

        bodyModel[248].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 2.5F, 0F, 0F, 2.5F, 0F, -1F, 3F, 0F, -1F, 3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 176
        bodyModel[248].setRotationPoint(25.5F, -18F, 3F);

        bodyModel[249].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 2.5F, 0F, 0F, 2.5F, 0F, 0F, 3F, 0F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 3F, 0F, -1F, 3F); // Box 404
        bodyModel[249].setRotationPoint(25.5F, -18F, -6F);

        bodyModel[250].addShapeBox(0F, 0F, 0F, 1, 4, 2, 0F,0F, -1F, -0.5F, 0F, -1F, -0.5F, 0F, -3F, 1F, 0F, -3F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 405
        bodyModel[250].setRotationPoint(25.5F, -19F, -9F);

        bodyModel[251].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 406
        bodyModel[251].setRotationPoint(17.75F, 0.5F, -6F);

        bodyModel[252].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 407
        bodyModel[252].setRotationPoint(18.5F, -5F, -8.5F);

        bodyModel[253].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 408
        bodyModel[253].setRotationPoint(20.5F, 0F, -8.5F);

        bodyModel[254].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 409
        bodyModel[254].setRotationPoint(22.75F, 0.5F, -6F);

        bodyModel[255].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 410
        bodyModel[255].setRotationPoint(23.5F, -5F, -8.5F);

        bodyModel[256].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 411
        bodyModel[256].setRotationPoint(19.25F, -18F, -5.5F);

        bodyModel[257].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 412
        bodyModel[257].setRotationPoint(24.25F, -18F, -5.5F);

        bodyModel[258].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 413
        bodyModel[258].setRotationPoint(15.5F, 0F, 4.5F);

        bodyModel[259].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 414
        bodyModel[259].setRotationPoint(18.5F, -5F, 4.5F);

        bodyModel[260].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 415
        bodyModel[260].setRotationPoint(17.75F, 0.5F, 5F);

        bodyModel[261].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 416
        bodyModel[261].setRotationPoint(23.5F, -5F, 4.5F);

        bodyModel[262].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 417
        bodyModel[262].setRotationPoint(20.5F, 0F, 4.5F);

        bodyModel[263].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 418
        bodyModel[263].setRotationPoint(22.75F, 0.5F, 5F);

        bodyModel[264].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 419
        bodyModel[264].setRotationPoint(15.5F, 0F, -8.5F);

        bodyModel[265].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 420
        bodyModel[265].setRotationPoint(24.25F, -18F, 4F);

        bodyModel[266].addShapeBox(0F, 0F, 0F, 1, 10, 10, 0F,0.2F, 0F, 0F, 0F, 0F, 2.5F, 0F, 0F, 2.5F, 0.2F, 0F, 0F, 0.2F, 0.5F, 0F, 0F, 0.5F, 2.5F, 0F, 0.5F, 2.5F, 0.2F, 0.5F, 0F); // Box 46
        bodyModel[266].setRotationPoint(-29F, -4.5F, -5F);

        bodyModel[267].addShapeBox(0F, 0F, 0F, 2, 10, 10, 0F,-0.38F, 0F, -3.25F, -1F, 0F, 0F, -1F, 0F, 0F, -0.38F, 0F, -3.25F, -0.38F, 0.5F, -3.25F, -1F, 0.5F, 0F, -1F, 0.5F, 0F, -0.38F, 0.5F, -3.25F); // Box 186
        bodyModel[267].setRotationPoint(-30.2F, -4.5F, -5F);

        bodyModel[268].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.15F, 0.25F, -1.95F, -0.5F, 0.25F, -2F, -0.5F, 0.25F, 0F, -0.5F, 0.25F, -0.5F, 0.9F, 0F, -0.35F, -0.5F, 0F, -2F, -0.5F, 0F, 0F, 0F, 0F, -0.75F); // Box 339
        bodyModel[268].setRotationPoint(-28F, -5.5F, 5.25F);

        bodyModel[269].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.8F, -0.4F, -2.45F, -1F, -0.4F, -2.3F, -0.5F, -0.4F, 0F, -0.9F, -0.4F, -0.25F, -0.15F, 1F, -1.95F, -1F, 1F, -2F, -0.5F, 1F, 0F, -0.5F, 1F, -0.5F); // Box 340
        bodyModel[269].setRotationPoint(-28F, -7.75F, 5.25F);

        bodyModel[270].addShapeBox(0F, 0F, 0F, 1, 10, 1, 0F,0F, 0F, 0.5F, 0.5F, 0F, 1.25F, 0.5F, 0F, -1.15F, 0F, 0F, -0.2F, 0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.5F, 0.5F, -1.15F, 0F, 0.5F, 0F); // Box 310
        bodyModel[270].setRotationPoint(-28F, -4.5F, -7F);

        bodyModel[271].addShapeBox(0F, 0F, 0F, 1, 10, 1, 0F,0F, 0F, 0F, 0.5F, 0F, -1F, 0.5F, 0F, 1F, 0F, 0F, 0.25F, 0F, 0.5F, 0F, 0.5F, 0.5F, -1F, 0.5F, 0.5F, 1F, 0F, 0.5F, 0.25F); // Box 312
        bodyModel[271].setRotationPoint(-28F, -4.5F, 6.25F);

        bodyModel[272].addShapeBox(0F, 0F, 0F, 2, 1, 11, 0F,-1.1F, -0.5F, -0.5F, -0.5F, -0.5F, -0.5F, -0.5F, -0.5F, -0.5F, -1.1F, -0.5F, -0.5F, -1.062F, 0F, -0.5F, -0.543F, 0F, -0.5F, -0.54F, 0F, -0.5F, -1.062F, 0F, -0.5F); // Box 314
        bodyModel[272].setRotationPoint(-29.3F, -16.5F, -5.5F);

        bodyModel[273].addShapeBox(0F, 0F, 0F, 5, 11, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F, 0.6F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F); // Box 319
        bodyModel[273].setRotationPoint(-21.5F, -15F, -9.5F);

        bodyModel[274].addShapeBox(0F, 0F, 0F, 5, 9, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.6F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F, 0.7F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F); // Box 320
        bodyModel[274].setRotationPoint(-21.5F, -3.5F, -9.5F);

        bodyModel[275].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, -0.3F, 0F, -0.2F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0.1F); // Box 322
        bodyModel[275].setRotationPoint(-21.5F, 6F, -10.1F);

        bodyModel[276].addShapeBox(0F, 0F, 0F, 1, 7, 1, 0F,-2.2F, 2F, -0.7F, 1.7F, 2F, -0.7F, 1.7F, 2F, 0.3F, -2.2F, 2F, 0.3F, 0.5F, -0.5F, -0.7F, -1F, -0.5F, -0.7F, -1F, -0.5F, 0.3F, 0.5F, -0.5F, 0.3F); // Box 282
        bodyModel[276].setRotationPoint(-29.15F, -11F, -1F);

        bodyModel[277].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 331
        bodyModel[277].setRotationPoint(7.75F, 0.5F, -6F);

        bodyModel[278].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 332
        bodyModel[278].setRotationPoint(8.5F, -5F, -8.5F);

        bodyModel[279].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 333
        bodyModel[279].setRotationPoint(5.5F, 0F, -8.5F);

        bodyModel[280].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 334
        bodyModel[280].setRotationPoint(9.25F, -18F, -5.5F);

        bodyModel[281].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 335
        bodyModel[281].setRotationPoint(12.75F, 0.5F, -6F);

        bodyModel[282].addShapeBox(0F, 0F, 0F, 1, 5, 4, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 336
        bodyModel[282].setRotationPoint(13.5F, -5F, -8.5F);

        bodyModel[283].addShapeBox(0F, 0F, 0F, 4, 1, 4, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 337
        bodyModel[283].setRotationPoint(10.5F, 0F, -8.5F);

        bodyModel[284].addShapeBox(0F, 0F, 0F, 0, 13, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 338
        bodyModel[284].setRotationPoint(14.25F, -18F, -5.5F);

        bodyModel[285].addShapeBox(0F, 0F, 0F, 1, 3, 14, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0.15F, 0F, 0F, 0.35F, 0F, 0F, 0.35F, 0F, 0.1F, 0.15F, 0F); // Box 342
        bodyModel[285].setRotationPoint(-26.4F, -16F, -7F);

        bodyModel[286].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.85F, -0.5F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.75F, -0.25F, 0F, -0.6F, -0.5F, 0F, -0.25F, -0.25F, 0F, 0F, -0.25F, 0F, -0.5F, -0.25F); // Box 170
        bodyModel[286].setRotationPoint(-29.95F, -0.9F, -1.75F);

        bodyModel[287].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.75F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.85F, -0.5F, 0F, -0.5F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.6F, -0.5F); // Box 171
        bodyModel[287].setRotationPoint(-29.95F, -0.9F, 0.75F);

        bodyModel[288].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, -0.9F, -0.4F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.6F, 0F, -0.9F, -0.6F, 0F, 0F, -0.4F, 0F, 0F, -0.5F, 0F, 0F, -0.6F, 0F, 0F, -0.6F); // Box 437
        bodyModel[288].setRotationPoint(-29.95F, -1.05F, -1.4F);

        bodyModel[289].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0F, -0.4F, 0F, 0F, -0.5F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.9F, -0.4F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.6F, 0F, -0.9F, -0.6F); // Box 438
        bodyModel[289].setRotationPoint(-29.95F, 1.5F, -1.4F);

        bodyModel[290].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, -1.25F, -0.5F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F, 0F, -1.25F, -0.5F, 0F, 0.75F, -0.5F, 0F, 1F, -0.5F, 0F, 1F, -0.5F, 0F, 0.75F, -0.5F); // lamp
        bodyModel[290].setRotationPoint(-29.95F, -1.3F, -1.5F);

        bodyModel[291].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Box 314
        bodyModel[291].setRotationPoint(-29.95F, -0.25F, -0.88F);

        bodyModel[292].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, -0.05F, 0F, -0.05F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 315
        bodyModel[292].setRotationPoint(-29.95F, -1F, -0.88F);

        bodyModel[293].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, -0.05F); // Box 318
        bodyModel[293].setRotationPoint(-29.95F, -0.25F, -0.12F);

        bodyModel[294].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Box 319
        bodyModel[294].setRotationPoint(-29.95F, -1F, -0.12F);

        bodyModel[295].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 318
        bodyModel[295].setRotationPoint(9F, -20F, -4.25F);

        bodyModel[296].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 319
        bodyModel[296].setRotationPoint(9F, -21F, -5F);

        bodyModel[297].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 320
        bodyModel[297].setRotationPoint(2F, -21F, -5F);

        bodyModel[298].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 321
        bodyModel[298].setRotationPoint(2F, -30.25F, -5F);

        bodyModel[299].addBox(0F, 0F, 0F, 1, 1, 10, 0F); // Box 322
        bodyModel[299].setRotationPoint(13F, -21F, -5F);

        bodyModel[300].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 323
        bodyModel[300].setRotationPoint(14F, -21F, -5F);

        bodyModel[301].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 324
        bodyModel[301].setRotationPoint(13F, -30.25F, -5F);

        bodyModel[302].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,0F, 0F, 0F, 1F, 5F, 0F, 1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F); // Box 325
        bodyModel[302].setRotationPoint(14F, -21F, 5F);

        bodyModel[303].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 326
        bodyModel[303].setRotationPoint(9F, -20F, 3.25F);

        bodyModel[304].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,0F, 2F, 0F, 1F, -4F, 0F, 1F, -4F, 0F, 0F, 2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F); // Box 327
        bodyModel[304].setRotationPoint(13F, -30.25F, 5F);

        bodyModel[305].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 328
        bodyModel[305].setRotationPoint(12F, -33.75F, 5.25F);

        bodyModel[306].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 329
        bodyModel[306].setRotationPoint(10F, -32.75F, 4F);

        bodyModel[307].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F); // Box 330
        bodyModel[307].setRotationPoint(10F, -33.75F, 5.25F);

        bodyModel[308].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 331
        bodyModel[308].setRotationPoint(10F, -33.75F, -4.75F);

        bodyModel[309].addShapeBox(0F, 0F, 0F, 1, 1, 10, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 332
        bodyModel[309].setRotationPoint(12F, -33.75F, -4.75F);

        bodyModel[310].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 333
        bodyModel[310].setRotationPoint(10F, -32.75F, -5F);

        bodyModel[311].addShapeBox(0F, 0F, 0F, 7, 1, 0, 0F,1F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 5F, 0F, 1F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, -5F, 0F); // Box 334
        bodyModel[311].setRotationPoint(2F, -21F, 5F);

        bodyModel[312].addShapeBox(0F, 0F, 0F, 8, 1, 0, 0F,1F, -4F, 0F, 0F, 2F, 0F, 0F, 2F, 0F, 1F, -4F, 0F, 1F, 4F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 1F, 4F, 0F); // Box 335
        bodyModel[312].setRotationPoint(2F, -30.25F, 5F);

        bodyModel[313].addShapeBox(0F, 0F, 0F, 5, 7, 0, 0F,0F, 0F, 0F, 0F, 0F, 3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 3F, 0F, 0F, -3F, 0F, 0F, 0F); // Box 336
        bodyModel[313].setRotationPoint(-32F, -16F, 11F);

        bodyModel[314].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0.25F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F, -1F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F); // Box 337
        bodyModel[314].setRotationPoint(-31.15F, -10F, 9.5F);
        bodyModel[314].rotateAngleY = -0.2268928F;

        bodyModel[315].addShapeBox(0F, 0F, 0F, 1, 4, 1, 0F,-1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -1F, 0F, -0.1F, -0.5F, -1F, -0.5F, 0F, 1F, 0F, 0F, 1F, -0.1F, -0.5F, -1F, -0.5F); // Box 341
        bodyModel[315].setRotationPoint(-27.45F, -11F, -8.2F);

        bodyModel[316].addShapeBox(0F, 0F, 0F, 1, 4, 1, 0F,-0.5F, -2.5F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, -0.5F, -2.5F, -0.25F, -0.5F, 1.5F, 0F, 0F, 1.5F, -0.1F, 0F, 1.5F, 0F, -0.5F, 1.5F, -0.5F); // Box 343
        bodyModel[316].setRotationPoint(-27.5F, -10F, 6.95F);

        bodyModel[317].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 344
        bodyModel[317].setRotationPoint(-30F, 0F, 3.8F);

        bodyModel[318].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, 0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F); // Box 345
        bodyModel[318].setRotationPoint(-30F, 0F, 4.55F);

        bodyModel[319].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F); // Box 346
        bodyModel[319].setRotationPoint(-30F, 0.75F, 3.8F);

        bodyModel[320].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F); // Box 347
        bodyModel[320].setRotationPoint(-30F, 0.75F, 4.55F);

        bodyModel[321].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Lamp
        bodyModel[321].setRotationPoint(-30.2F, 0F, 3.8F);

        bodyModel[322].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Lamp
        bodyModel[322].setRotationPoint(-30.2F, 0.75F, 4.55F);

        bodyModel[323].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Lamp
        bodyModel[323].setRotationPoint(-30.2F, 0.75F, 3.8F);

        bodyModel[324].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Lamp
        bodyModel[324].setRotationPoint(-30.2F, 0F, 4.55F);

        bodyModel[325].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F); // Lamp
        bodyModel[325].setRotationPoint(-30F, 0.75F, -4.8F);

        bodyModel[326].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F); // Lamp
        bodyModel[326].setRotationPoint(-30F, 0.75F, -5.55F);

        bodyModel[327].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F); // Box 361
        bodyModel[327].setRotationPoint(-29.8F, 0.75F, -5.55F);

        bodyModel[328].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 362
        bodyModel[328].setRotationPoint(-29.8F, 0F, -5.55F);

        bodyModel[329].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Lamp
        bodyModel[329].setRotationPoint(-30F, 0F, -5.55F);

        bodyModel[330].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.2F, -0.125F, 0F, 0.2F, -0.125F, 0F, -0.15F, -0.05F, 0F, -0.15F, -0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.2F, 0F, -0.125F, 0.2F); // Lamp
        bodyModel[330].setRotationPoint(-30F, 0F, -4.8F);

        bodyModel[331].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, 0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F); // Box 365
        bodyModel[331].setRotationPoint(-29.8F, 0F, -4.8F);

        bodyModel[332].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F); // Box 366
        bodyModel[332].setRotationPoint(-29.8F, 0.75F, -4.8F);

        bodyModel[333].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,1.1F, -0.5F, 0.9F, 0.5F, -0.5F, -1F, 0.5F, -0.5F, 1F, 0F, -0.5F, 0.25F, 0.5F, 0F, -0.25F, 0.5F, 0F, -1F, 0.5F, 0F, 1F, 0F, 0F, 0.25F); // Box 359
        bodyModel[333].setRotationPoint(-28F, 5.5F, 6.25F);

        bodyModel[334].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,0F, -0.25F, 0.2F, 0F, -0.25F, -0.55F, 0F, -0.25F, 0.55F, -0.5F, -0.25F, -0.2F, -0.4F, -0.2F, -0.1F, 0F, -0.2F, -0.6F, 0F, -0.2F, 0.3F, -0.5F, -0.2F, -0.5F); // Box 361
        bodyModel[334].setRotationPoint(-28.5F, 6.25F, 6.7F);

        bodyModel[335].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.5F, -0.5F, -0.2F, 0F, -0.5F, 0.425F, 0F, -0.5F, -0.55F, 0F, -0.5F, 0.2F, -0.5F, -0.2F, -0.5F, 0F, -0.2F, 0.35F, 0F, 0F, -0.6F, -0.4F, -0.2F, -0.1F); // Box 365
        bodyModel[335].setRotationPoint(-28.5F, 6F, -7.7F);

        bodyModel[336].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.5F, 0.25F, 0.5F, -0.5F, 1F, 0.5F, -0.5F, -1F, 1.1F, -0.5F, 0.9F, 0F, 0F, 0.25F, 0.5F, 0F, 0.865F, 0.5F, 0F, -1F, 0.5F, 0F, -0.25F); // Box 366
        bodyModel[336].setRotationPoint(-28F, 5.5F, -7.25F);

        bodyModel[337].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0.25F, 0.5F, 0F, 1F, 0.5F, 0F, -1F, 0.7F, 0F, 0F, 0F, -0.75F, 0.25F, 0.5F, -0.75F, 1F, 0.5F, -0.75F, -1F, 0.7F, -0.75F, 0F); // Box 367
        bodyModel[337].setRotationPoint(-28F, 4F, -7.26F);

        bodyModel[338].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -1.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1.5F, 0F, -0.75F, -1.75F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 1.5F); // Box 369
        bodyModel[338].setRotationPoint(-26.5F, 4F, -10.01F);

        bodyModel[339].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.7F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.75F, 0.5F, 0F, -0.75F, 0.7F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F); // Box 370
        bodyModel[339].setRotationPoint(-21.5F, 4F, -9.51F);

        bodyModel[340].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 371
        bodyModel[340].setRotationPoint(-16.5F, 4F, -10.21F);

        bodyModel[341].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.7F, 0F, 0F, 0.5F, 0F, -1F, 0.5F, 0F, 1F, 0F, 0F, 0.25F, 0.7F, -0.75F, 0F, 0.5F, -0.75F, -1F, 0.5F, -0.75F, 1F, 0F, -0.75F, 0.25F); // Box 372
        bodyModel[341].setRotationPoint(-28F, 4F, 6.26F);

        bodyModel[342].addShapeBox(0F, 0F, 0F, 21, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 376
        bodyModel[342].setRotationPoint(-16.51F, 4F, 9.21F);

        bodyModel[343].addShapeBox(0F, 0F, 0F, 12, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 377
        bodyModel[343].setRotationPoint(14.49F, 4F, 9.21F);

        bodyModel[344].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 378
        bodyModel[344].setRotationPoint(26.49F, 4F, 8.71F);

        bodyModel[345].addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 379
        bodyModel[345].setRotationPoint(26.49F, 4F, -9.69F);

        bodyModel[346].addShapeBox(-9F, -1F, -1F, 9, 1, 1, 0F,0F, 4F, 0F, 0F, -1F, 0F, 0F, -1F, 1F, 0F, 4F, 1F, 0F, -4F, 0F, 0F, 1F, 0F, 0F, 1F, 1F, 0F, -4F, 1F); // Box 58
        bodyModel[346].setRotationPoint(-10F, -21F, 0F);

        bodyModel[347].addShapeBox(1F, -1F, -3F, 19, 1, 12, 0F,1F, -2F, -1F, -9F, 4.5F, 1.5F, -9F, 4.5F, -4.5F, 1F, -2F, -7F, 1F, 2F, -1F, -9F, -4.5F, 1.5F, -9F, -4.5F, -4.5F, 1F, 2F, -7F); // Box 59
        bodyModel[347].setRotationPoint(-19.5F, -27F, 0F);

        bodyModel[348].addShapeBox(1F, -1F, -3F, 19, 1, 12, 0F,-9F, 4.5F, 1.5F, 1F, -2F, -1F, 1F, -2F, -7F, -9F, 4.5F, -4.5F, -9F, -4.5F, 1.5F, 1F, 2F, -1F, 1F, 2F, -7F, -9F, -4.5F, -4.5F); // Box 322
        bodyModel[348].setRotationPoint(-16.5F, -27F, 0F);

        bodyModel[349].addShapeBox(-9F, -1F, -1F, 9, 1, 1, 0F,0F, -1F, 0F, 0F, 4F, 0F, 0F, 4F, 1F, 0F, -1F, 1F, 0F, 1F, 0F, 0F, -4F, 0F, 0F, -4F, 1F, 0F, 1F, 1F); // Box 323
        bodyModel[349].setRotationPoint(4F, -21F, 0F);

        bodyModel[350].addShapeBox(0F, 0F, 0F, 1, 4, 1, 0F,-0.5F, -2.5F, -0.25F, 0F, 0F, 0F, 0F, 0F, -0.1F, -0.5F, -2.5F, -0.1F, -0.5F, 1.5F, -0.5F, 0F, 1.5F, 0F, 0F, 1.5F, -0.1F, -0.5F, 1.5F, 0F); // Box 379
        bodyModel[350].setRotationPoint(-27.5F, -10F, -8.05F);

        bodyModel[351].addShapeBox(0F, 0F, 0F, 48, 1, 5, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 377
        bodyModel[351].setRotationPoint(-21.5F, -20F, -8F);

        bodyModel[352].addShapeBox(0F, 0F, 0F, 48, 1, 5, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 378
        bodyModel[352].setRotationPoint(-21.5F, -20F, 3F);

        bodyModel[353].addShapeBox(0F, 0F, 0F, 48, 1, 6, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 379
        bodyModel[353].setRotationPoint(-21.5F, -20F, -3F);

        bodyModel[354].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0F, -1.5F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -1.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F); // Box 380
        bodyModel[354].setRotationPoint(-26.5F, -20F, -3F);

        bodyModel[355].addShapeBox(0F, 0F, 0F, 5, 1, 5, 0F,0F, -1.5F, 0F, 0F, -0.2F, 0F, 0F, -1F, 0F, 0F, -1.5F, -0.75F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, -0.75F); // Box 381
        bodyModel[355].setRotationPoint(-26.5F, -20F, 3F);

        bodyModel[356].addShapeBox(0F, 0F, 0F, 5, 1, 5, 0F,0F, -1.5F, -0.75F, 0F, -1F, 0F, 0F, -0.2F, 0F, 0F, -1.5F, 0F, 0F, 0.5F, -0.75F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F); // Box 382
        bodyModel[356].setRotationPoint(-26.5F, -20F, -8F);

        bodyModel[357].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.25F, 1.3F, -0.5F, -0.5F, 0.9F, 0F, 0.1F, 0.9F, -0.45F, -0.15F, 1.5F, -1.95F, -0.9F, 0.65F, -0.25F, -0.5F, 0.65F, 0F, -0.6F, 0.65F, -1.9F, -0.8F, 0.65F, -2.4F); // Box 383
        bodyModel[357].setRotationPoint(-28F, -11F, -8.25F);

        bodyModel[358].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 583
        bodyModel[358].setRotationPoint(12F, -33.75F, -6.75F);

        bodyModel[359].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Box 584
        bodyModel[359].setRotationPoint(10F, -33.75F, -6.75F);

        bodyModel[360].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F, 0.1F, 0F, 0F, 0.1F, -0.5F, 0F, -0.5F); // Box 385
        bodyModel[360].setRotationPoint(-18.5F, -21F, 6.2F);

        bodyModel[361].addShapeBox(0F, 0F, 0F, 38, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.15F, 0F, 0F, -0.15F); // Box 386
        bodyModel[361].setRotationPoint(-16.5F, -21F, 7.45F);

        bodyModel[362].addShapeBox(0F, 0F, 0F, 38, 2, 1, 0F,0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.25F, 0F, 0F, -0.25F); // Box 389
        bodyModel[362].setRotationPoint(-16.5F, -21F, -8.45F);

        bodyModel[363].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,-0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.4F, 0F, 0F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F); // Box 390
        bodyModel[363].setRotationPoint(-18.5F, -21F, -8.2F);

        bodyModel[364].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, -0.3F, 0F, 0F, -0.3F, 0F); // Box 389
        bodyModel[364].setRotationPoint(21.5F, -21F, -8.2F);

        bodyModel[365].addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, 0F); // Box 390
        bodyModel[365].setRotationPoint(21.5F, -21F, 6.2F);

        bodyModel[366].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.4F, 0.25F, -0.5F, -0.5F, 0.25F, 0F, -0.5F, 0.25F, -2F, -0.2F, 0.25F, -1.95F, 0F, 0F, -0.75F, -0.5F, 0F, 0F, -0.5F, 0F, -2F, 0.9F, 0F, -0.35F); // Box 391
        bodyModel[366].setRotationPoint(-28F, -5.5F, -8.25F);

        bodyModel[367].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.9F, -0.4F, -0.25F, -0.5F, -0.4F, 0F, -1F, -0.4F, -2.3F, -0.8F, -0.4F, -2.45F, -0.4F, 1F, -0.5F, -0.5F, 1F, 0F, -1F, 1F, -2F, -0.2F, 1F, -1.95F); // Box 392
        bodyModel[367].setRotationPoint(-28F, -7.75F, -8.25F);

        bodyModel[368].addShapeBox(0F, 0F, 0F, 1, 2, 7, 0F,-0.7F, -0.75F, 0.2F, 0.3F, -0.75F, 0.35F, -1.4F, -0.75F, 0F, 0.7F, -0.75F, 0F, 0.35F, 0F, -1.35F, -0.6F, 0F, -0.95F, -1.8F, 0F, 0F, 1.1F, 0F, 0F); // Box 395
        bodyModel[368].setRotationPoint(-28.5F, -6.5F, -7F);

        bodyModel[369].addShapeBox(0F, 0F, 0F, 1, 6, 7, 0F,-1.5F, 0.15F, -0.5F, 0.8F, 0.15F, -0.45F, 0.1F, 0.4F, 0F, -0.8F, 0.4F, 0F, -0.35F, -0.15F, 0.7F, -0.3F, -0.15F, 0.7F, -1.95F, -0.15F, 0F, 1.2F, -0.15F, 0F); // Box 396
        bodyModel[369].setRotationPoint(-27.5F, -13.2F, -7F);

        bodyModel[370].addShapeBox(0F, 0F, 0F, 90, 7, 1, 0F,0F, 0F, 0F, -50F, 0F, 0F, -50F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, -3F, 0F, -50F, -3F, 0F, -50F, -3F, -0.9F, 0F, -3F, -0.9F); // Box 239
        bodyModel[370].setRotationPoint(-17F, -22.5F, -8.8F);

        bodyModel[371].addShapeBox(0F, 0F, 0F, 90, 7, 1, 0F,0F, 0F, 0F, -50F, 0F, 0F, -50F, 0F, -0.9F, 0F, 0F, -0.9F, 0F, -3F, 0F, -50F, -3F, 0F, -50F, -3F, -0.9F, 0F, -3F, -0.9F); // Box 325
        bodyModel[371].setRotationPoint(-17F, -22.5F, 8.7F);

        bodyModel[372].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 393
        bodyModel[372].setRotationPoint(-29.9F, 0F, -2.25F);

        bodyModel[373].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F); // Box 394
        bodyModel[373].setRotationPoint(-29.9F, 0.75F, -2.25F);

        bodyModel[374].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F); // Box 395
        bodyModel[374].setRotationPoint(-29.9F, 0.75F, -1.5F);

        bodyModel[375].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, 0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F); // Box 396
        bodyModel[375].setRotationPoint(-29.9F, 0F, -1.5F);

        bodyModel[376].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 397
        bodyModel[376].setRotationPoint(-29.9F, 0F, 0.5F);

        bodyModel[377].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F); // Box 398
        bodyModel[377].setRotationPoint(-29.9F, 0.75F, 0.5F);

        bodyModel[378].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F); // Box 399
        bodyModel[378].setRotationPoint(-29.9F, 0.75F, 1.25F);

        bodyModel[379].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.05F, -0.05F, 0F, -0.05F, 0.05F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F); // Box 400
        bodyModel[379].setRotationPoint(-29.9F, 0F, 1.25F);

        bodyModel[380].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.05F, 0.05F, 0F, -0.05F, 0.05F, 0F, 0.3F, -0.125F, 0F, 0.3F, -0.125F, 0F, -0.125F, 0.3F, 0F, -0.125F, 0.3F, 0F, -0.125F, -0.125F, 0F, -0.125F, -0.125F); // Box 401
        bodyModel[380].setRotationPoint(-29.8F, 0F, 0.5F);

        bodyModel[381].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -2.2F, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 2F, 0F, -0.75F, -2.2F, 0F, -0.75F, -0.4F, 0F, -0.75F, 0F, 0F, -0.75F, 2F); // Box 615
        bodyModel[381].setRotationPoint(-26.5F, -16.1F, -10.51F);

        bodyModel[382].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 0.7F, 0F, 0F, 0.7F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.75F, 0.7F, 0F, -0.75F, 0.7F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F); // Box 617
        bodyModel[382].setRotationPoint(-21.5F, -16.1F, -9.41F);

        bodyModel[383].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0.1F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 618
        bodyModel[383].setRotationPoint(-16.5F, -16.1F, -10.11F);

        bodyModel[384].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.3F, 0F, 1.1F, 0.5F, 0F, -1F, 0.5F, 0F, 1.05F, -0.15F, 0F, 0.35F, 0.3F, -0.75F, 1.1F, 0.5F, -0.75F, -1F, 0.5F, -0.75F, 1.05F, -0.15F, -0.75F, 0.35F); // Box 619
        bodyModel[384].setRotationPoint(-28F, -16.1F, 6.35F);

        bodyModel[385].addShapeBox(0F, 0F, 0F, 43, 1, 1, 0F,0F, 0F, 0F, 0.025F, 0F, 0F, 0.025F, 0F, -0.05F, 0F, 0F, -0.05F, 0F, -0.75F, 0F, 0.025F, -0.75F, 0F, 0.025F, -0.75F, -0.05F, 0F, -0.75F, -0.05F); // Box 620
        bodyModel[385].setRotationPoint(-16.51F, -16.1F, 9.15F);

        bodyModel[386].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.15F, 0F, 0.35F, 0.5F, 0F, 1.05F, 0.5F, 0F, -1F, 0.3F, 0F, 1.05F, -0.15F, -0.75F, 0.35F, 0.5F, -0.75F, 1.05F, 0.5F, -0.75F, -1F, 0.3F, -0.75F, 1.05F); // Box 414
        bodyModel[386].setRotationPoint(-28F, -16.1F, -7.27F);

        bodyModel[387].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, 1.75F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -2F, 0F, -0.75F, 1.75F, 0F, -0.75F, 0F, 0F, -0.75F, -0.3F, 0F, -0.75F, -2F); // Box 415
        bodyModel[387].setRotationPoint(-26.5F, -16.1F, 9.39F);

        bodyModel[388].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.6F, 0F, 0F, 0.15F, 0F, 0F, 0.15F, 0F, -0.75F, -0.5F, 0F, -0.75F, -0.5F, 0F, -0.75F, 0.15F, 0F, -0.75F, 0.15F); // Box 416
        bodyModel[388].setRotationPoint(-21.5F, -16.1F, 8.95F);

        bodyModel[389].addShapeBox(0F, 0F, 0F, 1, 1, 9, 0F,-0.1F, 0F, 0.75F, -0.6F, 0F, 1F, -0.6F, 0F, 1F, -0.1F, 0F, 0.75F, -0.1F, -0.75F, 0.75F, -0.6F, -0.75F, 1F, -0.6F, -0.75F, 1F, -0.1F, -0.75F, 0.75F); // Box 417
        bodyModel[389].setRotationPoint(-28.4F, -16.1F, -4.5F);

        bodyModel[390].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 318
        bodyModel[390].setRotationPoint(26.5F, -19.5F, 2.5F);

        bodyModel[391].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 319
        bodyModel[391].setRotationPoint(26.5F, -19.5F, -2.5F);

        bodyModel[392].addShapeBox(0F, 0F, 0F, 4, 1, 5, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 320
        bodyModel[392].setRotationPoint(26.5F, -19.5F, -7.5F);

        bodyModel[393].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,-0.2F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0.25F, 0.1F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.25F, 0F, 0F); // Box 649
        bodyModel[393].setRotationPoint(-29.5F, -0.05F, -5.85F);

        bodyModel[394].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0.25F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, -0.2F, 0.1F, 0F, 0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F); // Box 650
        bodyModel[394].setRotationPoint(-29.5F, -0.05F, 2.85F);

        bodyModel[395].addShapeBox(0F, 0F, 0F, 2, 4, 2, 0F,-1.3F, 0F, -3.25F, -0.25F, 0F, -0.65F, 0F, 0F, 1.5F, -1.3F, 0F, 1.25F, -1.1F, -1.3F, -3.25F, -0.4F, -1.4F, -0.6F, 0F, -1.17F, 1.5F, -1.1F, -1.3F, 1.25F); // Box 432
        bodyModel[395].setRotationPoint(-29.5F, -16F, -8.25F);

        bodyModel[396].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0.25F, 0F, 0.35F, 0F, 0F, 1F, 0F, 0F, 0F, -0.21F, 0F, 0F, 0.4F, -0.4F, 0.4F, 0F, -0.65F, 1F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 433
        bodyModel[396].setRotationPoint(-27.5F, -16F, -7.25F);

        bodyModel[397].addShapeBox(0F, 0F, 0F, 2, 2, 3, 0F,-0.1F, -0.3F, -0.6F, -0.5F, -0.05F, 0F, 0.35F, -0.75F, 1F, 0.405F, -0.375F, 0.3F, -0.25F, -0.6F, -0.5F, -0.5F, -0.2F, 0F, 0.1F, -0.2F, -0.45F, -0.15F, -0.8F, -1.95F); // Box 434
        bodyModel[397].setRotationPoint(-28F, -13.7F, -8.25F);

        bodyModel[398].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,-0.25F, -0.8F, -0.4F, 0.25F, -0.7F, -0.4F, 0F, -0.7F, -0.5F, 0F, -0.8F, -0.6F, -0.25F, 0F, -0.4F, 0.25F, 0F, -0.4F, 0F, 0F, -0.5F, 0F, 0F, -0.6F); // Box 639
        bodyModel[398].setRotationPoint(-29.85F, -0.85F, -5.65F);

        bodyModel[399].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.85F, -0.5F, 0F, -0.8F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.65F, -0.25F, 0F, -0.7F, -0.5F, 0F, -0.55F, -0.25F, 0F, -0.3F, -0.25F, 0F, -0.5F, -0.25F); // Box 640
        bodyModel[399].setRotationPoint(-29.6F, -0.7F, -6F);

        bodyModel[400].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.7F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.85F, -0.5F, 0F, -0.5F, -0.25F, 0F, -0.35F, -0.25F, 0F, -0.35F, -0.25F, 0F, -0.7F, -0.5F); // Box 641
        bodyModel[400].setRotationPoint(-29.85F, -0.7F, -3.5F);

        bodyModel[401].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,-0.25F, -1.25F, -0.5F, 0F, -1F, -0.5F, 0F, -1F, -0.5F, 0F, -1.25F, -0.5F, -0.25F, 0.75F, -0.5F, 0F, 0.75F, -0.5F, 0F, 0.75F, -0.5F, 0F, 0.75F, -0.5F); // Lamp
        bodyModel[401].setRotationPoint(-29.85F, -1.1F, -5.75F);

        bodyModel[402].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,-0.25F, 0.1F, -0.4F, 0.25F, 0F, -0.4F, 0F, 0F, -0.6F, 0F, 0.1F, -0.6F, -0.25F, -0.9F, -0.4F, 0.25F, -0.7F, -0.4F, 0F, -0.7F, -0.6F, 0F, -0.9F, -0.6F); // Box 643
        bodyModel[402].setRotationPoint(-29.85F, 1.7F, -5.65F);

        bodyModel[403].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, -0.8F, -0.4F, 0F, -0.9F, -0.4F, 0.25F, -0.9F, -0.5F, -0.25F, -0.8F, -0.6F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0.25F, 0F, -0.5F, -0.25F, 0F, -0.6F); // Box 644
        bodyModel[403].setRotationPoint(-30.05F, -0.85F, 2.85F);

        bodyModel[404].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, -1.25F, -0.5F, 0F, -1.2F, -0.5F, 0F, -1.2F, -0.5F, -0.25F, -1.25F, -0.5F, 0F, 0.75F, -0.5F, 0F, 0.75F, -0.5F, 0F, 0.75F, -0.5F, -0.25F, 0.75F, -0.5F); // Lamp
        bodyModel[404].setRotationPoint(-30.05F, -1.1F, 2.75F);

        bodyModel[405].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.65F, -0.25F, 0F, -0.7F, -0.25F, 0F, -0.9F, -0.25F, 0F, -0.85F, -0.5F, 0F, -0.5F, -0.25F, 0F, -0.35F, -0.25F, 0F, -0.35F, -0.25F, 0F, -0.7F, -0.5F); // Box 646
        bodyModel[405].setRotationPoint(-29.8F, -0.7F, 5F);

        bodyModel[406].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0.1F, -0.4F, 0F, 0F, -0.4F, 0.25F, 0F, -0.6F, -0.25F, 0.1F, -0.6F, 0F, -0.9F, -0.4F, 0F, -0.7F, -0.4F, 0.25F, -0.7F, -0.6F, -0.25F, -0.9F, -0.6F); // Box 647
        bodyModel[406].setRotationPoint(-30.05F, 1.7F, 2.85F);

        bodyModel[407].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,0F, -0.85F, -0.5F, 0F, -0.7F, -0.25F, 0F, -0.6F, -0.25F, 0F, -0.65F, -0.25F, 0F, -0.7F, -0.5F, 0F, -0.35F, -0.25F, 0F, -0.3F, -0.25F, 0F, -0.5F, -0.25F); // Box 648
        bodyModel[407].setRotationPoint(-30.05F, -0.7F, 2.5F);

        bodyModel[408].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.9F, 0.35F, -0.25F, -0.5F, 0.35F, 0F, -0.65F, 0.35F, 0.05F, -0.8F, 0.35F, -0.4F, -0.9F, 0.65F, -0.25F, -0.5F, 0.65F, 0F, -1F, 0.65F, -0.3F, -0.8F, 0.65F, -0.45F); // Box 431
        bodyModel[408].setRotationPoint(-28F, -9F, -8.25F);

        bodyModel[409].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,-0.15F, 1.5F, -1.95F, 0.1F, 0.9F, -0.45F, -0.5F, 0.9F, 0F, -0.25F, 1.3F, -0.5F, -0.8F, 0.65F, -2.4F, -0.65F, 0.65F, -1.95F, -0.5F, 0.65F, 0F, -0.9F, 0.65F, -0.25F); // Box 432
        bodyModel[409].setRotationPoint(-28F, -11F, 5.25F);

        bodyModel[410].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F,-0.8F, 0.35F, -0.4F, -0.65F, 0.35F, 0.05F, -0.5F, 0.35F, 0F, -0.9F, 0.35F, -0.25F, -0.8F, 0.65F, -0.45F, -1F, 0.65F, -0.3F, -0.5F, 0.65F, 0F, -0.9F, 0.65F, -0.25F); // Box 433
        bodyModel[410].setRotationPoint(-28F, -9F, 7.25F);

        bodyModel[411].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.7F, 0.2F, 0F, 0.7F, 0.2F); // Box 651
        bodyModel[411].setRotationPoint(-18.5F, -21F, 3F);

        bodyModel[412].addShapeBox(0F, 0F, 0F, 1, 1, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F); // Box 652
        bodyModel[412].setRotationPoint(-18.5F, -21F, -3F);

        bodyModel[413].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.7F, 0.2F, 0F, 0.7F, 0.2F, 0F, 0.1F, 0F, 0F, 0.1F, 0F); // Box 653
        bodyModel[413].setRotationPoint(-18.5F, -21F, -6F);

        bodyModel[414].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.7F, 0.2F, 0F, 0.7F, 0.2F, 0F, 0.1F, 0F, 0F, 0.1F, 0F); // Box 436
        bodyModel[414].setRotationPoint(22.5F, -21F, -6F);

        bodyModel[415].addShapeBox(0F, 0F, 0F, 1, 1, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F); // Box 437
        bodyModel[415].setRotationPoint(22.5F, -21F, -3F);

        bodyModel[416].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.7F, 0.2F, 0F, 0.7F, 0.2F); // Box 438
        bodyModel[416].setRotationPoint(22.5F, -21F, 3F);

        bodyModel[417].addShapeBox(0F, 0F, 0F, 2, 2, 3, 0F,0.405F, -0.375F, 0.3F, 0.1F, -0.75F, 1F, -0.5F, -0.05F, 0F, -0.1F, -0.3F, -0.6F, -0.15F, -0.8F, -1.95F, 0.1F, -0.2F, -0.45F, -0.5F, -0.2F, 0F, -0.25F, -0.6F, -0.5F); // Box 424
        bodyModel[417].setRotationPoint(-28F, -13.7F, 5.25F);

        bodyModel[418].addShapeBox(0F, 0F, 0F, 2, 4, 2, 0F,-1.3F, 0F, 1.25F, 0F, 0F, 1.5F, -0.25F, 0F, -0.65F, -1.3F, 0F, -3.25F, -1.1F, -1.3F, 1.25F, 0F, -1.17F, 1.5F, -0.4F, -1.4F, -0.6F, -1.1F, -1.3F, -3.25F); // Box 425
        bodyModel[418].setRotationPoint(-29.5F, -16F, 6.25F);

        bodyModel[419].addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F,-0.21F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0.25F, 0F, 0.35F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.65F, 1F, 0.4F, -0.4F, 0.4F); // Box 426
        bodyModel[419].setRotationPoint(-27.5F, -16F, 6.25F);

        bodyModel[420].addShapeBox(0F, 0F, 0F, 1, 2, 7, 0F,-0.85F, -0.4F, 0.7F, 0.2F, -0.4F, 0.7F, -1.4F, -0.4F, 0F, 0.7F, -0.4F, 0F, -0.2F, 0F, 0.2F, -0.2F, 0F, 0.35F, -1.9F, 0F, 0F, 1.2F, 0F, 0F); // Box 422
        bodyModel[420].setRotationPoint(-28F, -7.75F, -7F);

        bodyModel[421].addShapeBox(0F, 0F, 0F, 1, 2, 7, 0F,0.7F, -0.75F, 0F, -1.4F, -0.75F, 0F, 0.2F, -0.75F, 0.35F, -0.7F, -0.75F, 0.2F, 1.1F, 0F, 0F, -1.8F, 0F, 0F, -0.6F, 0F, -0.95F, 0.35F, 0F, -1.35F); // Box 423
        bodyModel[421].setRotationPoint(-28.5F, -6.5F, 0F);

        bodyModel[422].addShapeBox(0F, 0F, 0F, 1, 2, 7, 0F,0.7F, -0.4F, 0F, -1.4F, -0.4F, 0F, 0.2F, -0.4F, 0.7F, -0.85F, -0.4F, 0.7F, 1.2F, 0F, 0F, -1.9F, 0F, 0F, -0.3F, 0F, 0.35F, -0.2F, 0F, 0.2F); // Box 424
        bodyModel[422].setRotationPoint(-28F, -7.75F, 0F);

        bodyModel[423].addShapeBox(0F, 0F, 0F, 1, 6, 7, 0F,-0.8F, 0.4F, 0F, 0.1F, 0.4F, 0F, 1F, 0.15F, -0.45F, -1.5F, 0.15F, -0.5F, 1.2F, -0.15F, 0F, -1.9F, -0.15F, 0F, -0.3F, -0.15F, 0.7F, -0.35F, -0.15F, 0.7F); // Box 425
        bodyModel[423].setRotationPoint(-27.5F, -13.2F, 0F);

        bodyModel[424].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,-0.062F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, -1F, -0.062F, -0.2F, -1F, 0.07F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0.07F, 0F, -1F); // Box 425
        bodyModel[424].setRotationPoint(-28.3F, -15.7F, -5F);

        bodyModel[425].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,-0.062F, -0.2F, -1F, 0F, -0.2F, -1F, 0F, -0.2F, 0F, -0.062F, -0.2F, 0F, 0.07F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0.07F, 0F, 0F); // Box 426
        bodyModel[425].setRotationPoint(-28.3F, -15.7F, 3F);
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		for (int i = 0; i < 426; i++) {
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