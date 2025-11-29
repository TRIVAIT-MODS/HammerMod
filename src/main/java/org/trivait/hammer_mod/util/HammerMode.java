package org.trivait.hammer_mod.util;

import net.minecraft.util.Identifier;

public enum HammerMode {
    DEFAULT("3×3",
            Identifier.of("hammer_mod", "textures/gui/mode_default.png"),
            Identifier.of("hammer_mod", "textures/gui/mode_default_selected.png")),
    CUBE("3×3×3",
            Identifier.of("hammer_mod", "textures/gui/mode_cube.png"),
            Identifier.of("hammer_mod", "textures/gui/mode_cube_selected.png")),
    SINGLE("1×1",
            Identifier.of("hammer_mod", "textures/gui/mode_single.png"),
            Identifier.of("hammer_mod", "textures/gui/mode_single_selected.png"));

    private final String displayName;
    private final Identifier texture;
    private final Identifier textureSelected;

    HammerMode(String displayName, Identifier texture, Identifier textureSelected) {
        this.displayName = displayName;
        this.texture = texture;
        this.textureSelected = textureSelected;
    }

    public String getDisplayName() { return displayName; }
    public Identifier getTexture() { return texture; }
    public Identifier getTextureSelected() { return textureSelected; }

    public HammerMode next() {
        return switch (this) {
            case DEFAULT -> CUBE;
            case CUBE -> SINGLE;
            case SINGLE -> DEFAULT;
        };
    }
    public HammerMode prev() {
        return switch (this) {
            case DEFAULT -> SINGLE;
            case CUBE -> DEFAULT;
            case SINGLE -> CUBE;
        };
    }
}
