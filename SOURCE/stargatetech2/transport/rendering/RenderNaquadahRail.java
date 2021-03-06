package stargatetech2.transport.rendering;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import stargatetech2.core.base.BaseISBRH;
import stargatetech2.enemy.ModuleEnemy;
import stargatetech2.transport.block.BlockNaquadahRail;

public class RenderNaquadahRail extends BaseISBRH {
	private static final RenderNaquadahRail INSTANCE = new RenderNaquadahRail();
	
	public static RenderNaquadahRail instance(){
		return INSTANCE;
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(BlockNaquadahRail.currentRenderPass == 0){
			BlockNaquadahRail rail = (BlockNaquadahRail) block;
			rail.toggleRenderOverride();
			renderer.renderBlockByRenderType(rail, x, y, z);
			rail.toggleRenderOverride();
		}else{
			if((world.getBlockMetadata(x, y, z) & 8) != 0){
				renderer.setRenderBoundsFromBlock(ModuleEnemy.shield);
				renderer.renderStandardBlock(ModuleEnemy.shield, x, y, z);
			}
		}
		return true;
	}
	
	@Override
	public boolean shouldRender3DInInventory(){
		return false;
	}
}