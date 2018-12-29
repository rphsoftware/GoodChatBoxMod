package space.rph.goodchatbox.tileentity;

public class TileChatboxT1 extends TileEntityChatbox {
    public TileChatboxT1() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 6);
    }

    @Override
    public boolean getInterdimensional() { return false;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return false;}
}
