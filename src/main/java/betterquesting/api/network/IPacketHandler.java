package betterquesting.api.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IPacketHandler
{
	public ResourceLocation getRegistryName();
	
	public void handleServer(NBTTagCompound tag, EntityPlayerMP sender);
	
	@SideOnly(Side.CLIENT)
	public void handleClient(NBTTagCompound tag);
}
