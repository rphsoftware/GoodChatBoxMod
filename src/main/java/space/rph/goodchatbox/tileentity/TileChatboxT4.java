package space.rph.goodchatbox.tileentity;

public class TileChatboxT4 extends TileEntityChatbox {
    public TileChatboxT4() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 9);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return false;}
}
