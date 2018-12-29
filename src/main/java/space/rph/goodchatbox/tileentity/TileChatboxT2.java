package space.rph.goodchatbox.tileentity;

public class TileChatboxT2 extends TileEntityChatbox {
    public TileChatboxT2() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 7);
    }

    @Override
    public boolean getInterdimensional() { return false;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return false;}
}
