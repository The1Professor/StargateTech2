package stargatetech2.core.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import stargatetech2.core.base.BaseContainer;
import stargatetech2.enemy.gui.ContainerParticleIonizer;
import stargatetech2.enemy.gui.ContainerShieldController;
import stargatetech2.enemy.tileentity.TileParticleIonizer;
import stargatetech2.enemy.tileentity.TileShieldController;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
	public enum Screen{
		SHIELD_CONTROLLER,
		PARTICLE_IONIZER,
		PRIORITIZER,
		CROSSOVER
	}
	
	@Override
	public BaseContainer getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		BaseContainer container = null;
		switch(Screen.values()[ID]){
			case SHIELD_CONTROLLER:
				if(te instanceof TileShieldController)
					container = new ContainerShieldController((TileShieldController)te);
				break;
			case PARTICLE_IONIZER:
				if(te instanceof TileParticleIonizer)
					container = new ContainerParticleIonizer((TileParticleIonizer)te, player);
				break;
			default: break;
		}
		return container;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		return null;
	}
}