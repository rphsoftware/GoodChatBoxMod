package space.rph.goodchatbox.tileentity;

public class TileChatboxT12 extends TileEntityChatbox {
    public TileChatboxT12() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 17);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return true;}
}
