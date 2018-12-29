package space.rph.goodchatbox.tileentity;

public class TileChatboxT6 extends TileEntityChatbox {
    public TileChatboxT6() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 11);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return false;}
}
