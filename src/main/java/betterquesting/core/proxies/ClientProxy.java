package betterquesting.core.proxies;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import betterquesting.api.placeholders.EntityPlaceholder;
import betterquesting.client.BQ_Keybindings;
import betterquesting.client.QuestNotification;
import betterquesting.client.renderer.EntityPlaceholderRenderer;
import betterquesting.client.toolbox.ToolboxRegistry;
import betterquesting.client.toolbox.ToolboxTabMain;
import betterquesting.core.BetterQuesting;
import betterquesting.core.ExpansionLoader;
import betterquesting.misc.QuestResourcesFile;
import betterquesting.misc.QuestResourcesFolder;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class ClientProxy extends CommonProxy
{
	@Override
	public boolean isClient()
	{
		return true;
	}
	
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void registerHandlers()
	{
		super.registerHandlers();
		
		//FMLCommonHandler.instance().bus().register(new UpdateNotification());
		
		ExpansionLoader.INSTANCE.initClientAPIs();
		
		MinecraftForge.EVENT_BUS.register(new QuestNotification());
		BQ_Keybindings.RegisterKeys();
		
		try
		{
			ArrayList list = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao");
			QuestResourcesFolder qRes1 = new QuestResourcesFolder();
			QuestResourcesFile qRes2 = new QuestResourcesFile();
			list.add(qRes1);
			list.add(qRes2);
			((SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).reloadResourcePack(qRes1); // Make sure the pack(s) are visible to everything
			((SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).reloadResourcePack(qRes2); // Make sure the pack(s) are visible to everything
		} catch(Exception e)
		{
			BetterQuesting.logger.log(Level.ERROR, "Unable to install questing resource loaders", e);
		}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityPlaceholder.class, new EntityPlaceholderRenderer());
		
		ToolboxRegistry.INSTANCE.registerToolbox(ToolboxTabMain.instance);
	}
}
