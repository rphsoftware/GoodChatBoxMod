package space.rph.goodchatbox.tileentity;

public class TileChatboxT5 extends TileEntityChatbox {
    public TileChatboxT5() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 10);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return false;}
}
