package space.rph.goodchatbox.tileentity;

public class TileChatboxT10 extends TileEntityChatbox {
    public TileChatboxT10() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 15);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return true;}
}
