package space.rph.goodchatbox.tileentity;

public class TileChatboxT13 extends TileEntityChatbox {
    public TileChatboxT13() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 18);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return true;}
}
