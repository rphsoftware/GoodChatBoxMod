package space.rph.goodchatbox.tileentity;

public class TileChatboxCreative extends TileEntityChatbox {
    public TileChatboxCreative() {
        super();
    }

    @Override
    public double getRange() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return true;}

    @Override
    public boolean getAllowExtended() { return true;}
}
