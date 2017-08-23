package ru.alastar.minedonate.gui.merge;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.alastar.minedonate.MineDonate;
import ru.alastar.minedonate.rtnl.ModNetwork;

public class ShopInventoryGUI extends GuiContainer {

    ResourceLocation mdinvGuiTexture = new ResourceLocation("minedonate", "textures/mdinv.png");

	ShopInventoryContainer sic ;
	GuiButton acceptButton ;
	
	public ShopInventoryGUI ( InventoryPlayer _ip ) {

		super ( new ShopInventoryContainer ( _ip ) ) ;
					
		sic = ( ShopInventoryContainer ) this . inventorySlots ;
		 
	}

	ScaledResolution resolution ;
	
	@Override
	public void initGui ( ) {
	
		super . initGui ( ) ;

		resolution = new ScaledResolution ( this . mc, this . mc . displayWidth, this . mc . displayHeight ) ;
				
		this . buttonList . add ( ( acceptButton = new GuiButton ( this . buttonList . size ( ), ( (  this . width ) / 2 ) - ( MineDonate . cfgUI . mergeButton . width / 2 ), ( ( this . height + this . ySize ) / 2 ) - 85 - MineDonate . cfgUI . mergeButton . height, MineDonate . cfgUI . mergeButton . width, MineDonate . cfgUI . mergeButton . height, MineDonate . cfgUI . mergeButton . text ) ) ) ;
	
		sic . mdInv . uis = new AcceptButtonUpdater ( acceptButton ) ;
		
		acceptButton . enabled = false ;
		
	}
	

    @Override
    protected void actionPerformed ( GuiButton b ) {

    	super . actionPerformed ( b ) ;
    	
    	if ( b . id == acceptButton . id && sic . mdInv . getStackInSlot ( 0 ) != null ) {
    	
    		ModNetwork . sendToServerCloseShopInventoryPacket ( ) ;
    		
    	}
    	
    }
    
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
		this.mc.getTextureManager().bindTexture(mdinvGuiTexture);
        
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        this.fontRendererObj.drawString( "MineDonate", k+8, l+5, -10132123);
        
	}

	
}
