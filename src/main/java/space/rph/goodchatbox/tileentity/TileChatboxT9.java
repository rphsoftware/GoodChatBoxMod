package space.rph.goodchatbox.tileentity;

public class TileChatboxT9 extends TileEntityChatbox {
    public TileChatboxT9() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 14);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return true;}
}
