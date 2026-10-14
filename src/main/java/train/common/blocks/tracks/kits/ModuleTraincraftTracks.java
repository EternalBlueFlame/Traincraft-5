package train.common.blocks.tracks.kits;

import mods.railcraft.api.core.IRailcraftModule;
import mods.railcraft.api.core.RailcraftModule;

/**
 * Railcraft module that registers Traincraft's track kits and track types.
 * <p>
 * Railcraft finds this class by its {@link RailcraftModule} annotation and runs it during its own
 * loading. That is the only place where Railcraft 12 allows track kits/types to be registered.
 * When Railcraft is not installed, nobody loads this class, so the whole track kit package stays unused.
 * Players can turn it off in {@code config/railcraft/modules/tc_tracks.cfg}.
 */
@RailcraftModule(value = "tc:tracks", description = "Traincraft track kits and copper/steel/snowy track types",
        dependencies = {"railcraft:tracks"})
public class ModuleTraincraftTracks implements IRailcraftModule {

    @Override
    public ModuleEventHandler getModuleEventHandler(boolean enabled) {
        if (!enabled) {
            return new ModuleEventHandler() {
            };
        }
        return new ModuleEventHandler() {
            @Override
            public void preInit() {
                TCTrackTypes.registerAll();
                TCTrackKits.registerAll();
            }

            @Override
            public void init() {
                TCTrackTypes.defineRecipes();
                TCTrackKits.defineRecipes();
            }
        };
    }
}
