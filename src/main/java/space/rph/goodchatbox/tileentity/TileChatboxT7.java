package space.rph.goodchatbox.tileentity;

public class TileChatboxT7 extends TileEntityChatbox {
    public TileChatboxT7() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 12);
    }

    @Override
    public boolean getInterdimensional() { return true;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return false;}
}
