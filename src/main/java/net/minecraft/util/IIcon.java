package net.minecraft.util;

public interface IIcon {
	default float getMinU() {
		return 0;
	}

	default float getMaxU() {
		return 0;
	}

	default float getMinV() {
		return 0;
	}

	default float getMaxV() {
		return 0;
	}

	default int getIconWidth() {
		return 0;
	}

	default int getIconHeight() {
		return 0;
	}

	default String getIconName() {
		return "";
	}
}
