package stargatetech2.core.machine;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import stargatetech2.core.base.BaseTileEntity;
import stargatetech2.core.packet.PacketUpdateMachineColors;

public abstract class TileMachine extends BaseTileEntity {
	private FaceColor[] colors = new FaceColor[]{
			FaceColor.VOID, FaceColor.VOID,
			FaceColor.VOID, FaceColor.VOID,
			FaceColor.VOID, FaceColor.VOID,
	};
	
	protected abstract FaceColor[] getPossibleFaceColors();
	
	public final boolean hasColor(FaceColor color){
		for(FaceColor c : colors){
			if(c == color) return true;
		}
		return false;
	}
	
	@SuppressWarnings("incomplete-switch")
	public final int getSideFromFace(Face face){
		ForgeDirection dir = null;
		if(face == Face.TOP){
			dir = ForgeDirection.UP;
		}else if(face == Face.BOTTOM){
			dir = ForgeDirection.DOWN;
		}else if(face == Face.FRONT){
			dir = ForgeDirection.getOrientation(getBlockMetadata());
		}else if(face == Face.BACK){
			dir = ForgeDirection.getOrientation(getBlockMetadata()).getOpposite();
		}else{
			dir = ForgeDirection.getOrientation(getBlockMetadata());
			ForgeDirection left = ForgeDirection.UNKNOWN;
			switch(dir){
				case EAST:
					left = ForgeDirection.SOUTH;
					break;
				case NORTH:
					left = ForgeDirection.EAST;
					break;
				case SOUTH:
					left = ForgeDirection.WEST;
					break;
				case WEST:
					left = ForgeDirection.NORTH;
					break;
			}
			dir = (face == Face.LEFT) ? left : left.getOpposite();
		}
		return dir.ordinal();
	}
	
	public final void toggleFace(Face face){
		FaceColor[] possibleColors = getPossibleFaceColors();
		int side = getSideFromFace(face);
		FaceColor currentColor = getColor(side);
		for(int index = 0; index < possibleColors.length; index++){
			if(currentColor == possibleColors[index]){
				int next = (index + 1) % possibleColors.length;
				setColor(side, possibleColors[next]);
				break;
			}
		}
	}
	
	public final FaceColor getColor(Face face){
		return getColor(getSideFromFace(face));
	}
	
	public final FaceColor getColor(ForgeDirection side){
		return getColor(side.ordinal());
	}
	
	public final FaceColor getColor(int side) {
		return colors[side];
	}
	
	protected void setColor(int side, FaceColor color){
		colors[side] = color != null ? color : FaceColor.VOID;
		forceRedraw();
	}
	
	protected final void forceRedraw(){
		if(!worldObj.isRemote){
			PacketUpdateMachineColors update = new PacketUpdateMachineColors(colors);
			update.x = xCoord;
			update.y = yCoord;
			update.z = zCoord;
			update.sendToAllInDim(worldObj.provider.dimensionId);
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
		}
	}
	
	public final void updateColors(FaceColor[] colors){
		this.colors = colors;
		worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
	}
	
	protected final NBTTagCompound writeFacingNBT(){
		NBTTagCompound nbt = new NBTTagCompound();
		for(int i = 0; i < colors.length; i++){
			nbt.setByte("color" + i, (byte) colors[i].ordinal());
		}
		return nbt;
	}
	
	protected final void readFacingNBT(NBTTagCompound nbt){
		for(int i = 0; i < colors.length; i++){
			colors[i] = FaceColor.values()[nbt.getByte("color" + i)];
		}
	}
}