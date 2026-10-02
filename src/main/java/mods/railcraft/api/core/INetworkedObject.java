/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface INetworkedObject<I extends DataInputStream, O extends DataOutputStream> extends IWorldSupplier {

    default void readPacketData(I data) throws IOException { }

    default void writePacketData(O data) throws IOException { }

    void sendUpdateToClient();

}
