package space.rph.goodchatbox.tileentity;

public class TileChatboxT3 extends TileEntityChatbox {
    public TileChatboxT3() {
        super();
    }

    @Override
    public double getRange() {
        return Math.pow(2, 8);
    }

    @Override
    public boolean getInterdimensional() { return false;}

    @Override
    public boolean getAllowLowLevel() { return false;}

    @Override
    public boolean getAllowExtended() { return false;}
}
