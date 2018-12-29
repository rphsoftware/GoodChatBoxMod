package space.rph.goodchatbox.tileentity;

public class TileChatboxT8 extends TileEntityChatbox {
    public TileChatboxT8() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 13);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return true;}
}
