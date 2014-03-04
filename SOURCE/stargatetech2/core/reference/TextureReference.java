package stargatetech2.core.reference;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;

public class TextureReference {
	public static final ArrayList<String> TERRAIN_TEXTURES = new ArrayList<String>();
	public static final ArrayList<String> ITEM_TEXTURES = new ArrayList<String>();
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	private static @interface Block{}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	private static @interface Item{}
	
	//### BLOCKS ###
	@Block public static final String IONIZED_PARTICLES = "ionizedParticles";
	
	@Block public static final String MACHINE_SIDE = "machineSide";
	@Block public static final String MACHINE_SIDE_I = "machineSideI";
	@Block public static final String MACHINE_TOP = "machineTop";
	@Block public static final String MACHINE_TOP_I = "machineTopI";
	@Block public static final String MACHINE_BOTTOM = "machineBottom";
	@Block public static final String MACHINE_BOTTOM_I = "machineBottomI";
	
	@Block public static final String INTERFACE_BLUE	= "interfaceBlue";
	@Block public static final String INTERFACE_PURPLE	= "interfacePurple";
	@Block public static final String INTERFACE_GREEN	= "interfaceGreen";
	@Block public static final String INTERFACE_ORANGE	= "interfaceOrange";
	@Block public static final String INTERFACE_YELLOW	= "interfaceYellow";
	@Block public static final String INTERFACE_RED	= "interfaceRed";
	
	@Block public static final String FACE_PARTICLE_IONIZER = "faceParticleIonizer";
	@Block public static final String GLOW_PARTICLE_IONIZER = "glowParticleIonizer";
	
	@Block public static final String FACE_SHIELD_EMITTER = "faceShieldEmitter";
	@Block public static final String GLOW_SHIELD_EMITTER = "glowShieldEmitter";
	
	@Block public static final String FACE_NAQUADAH_ORE = "faceNaquadahOre";
	@Block public static final String GLOW_NAQUADAH_ORE = "glowNaquadahOre";
	
	@Block public static final String LANTEAN_BLOCK_CLEAN = "lanteanBlockClean";
	@Block public static final String STARGATE_BASE_TOP = "stargateBaseTop";
	@Block public static final String TEXTURE_INVISIBLE = "invisible";
	@Block public static final String BUS_CABLE_PLUG = "busPlug";
	
	static{
		try{
			Field[] fields = TextureReference.class.getFields();
			for(Field field : fields){
				if(field.isAnnotationPresent(Block.class)){
					addField(field, TERRAIN_TEXTURES);
				}else if(field.isAnnotationPresent(Item.class)){
					addField(field, ITEM_TEXTURES);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void addField(Field field, ArrayList<String> list) throws Exception{
		String value = (String) field.get(null);
		list.add(value);
	}
	
	public static final ResourceLocation GUI_BASE = getTexture("gui/base.png");
	public static final ResourceLocation GUI_SHIELD_EMITTER = getTexture("gui/shieldEmitter.png");
	public static final ResourceLocation GUI_PARTICLE_IONIZER = getTexture("gui/particleIonizer.png");
	public static final ResourceLocation GUI_NAQUADAH_CAPACITOR = getTexture("gui/naquadahCapacitor.png");
	
	public static final ResourceLocation TESR_TRANSPORT_RING = getTexture("special/transportRing.png");
	public static final ResourceLocation TESR_STARGATE = getTexture("special/stargate.png");
	public static final ResourceLocation EVENT_HORIZON = getTexture("special/eventHorizon.png");
	public static final ResourceLocation CHEVRONS = getTexture("special/chevrons.png");
	public static final ResourceLocation SYMBOLS = getTexture("special/symbols.png");
	
	public static ResourceLocation getTexture(String texture){
		return new ResourceLocation(ModReference.MOD_ID + ":" + "textures/" + texture);
	}
}