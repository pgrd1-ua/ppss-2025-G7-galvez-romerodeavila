package ppss.P06;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.easymock.*;
import static org.junit.jupiter.api.Assertions.*;
public class NotifyCenterTest {

    NotifyCenter notifyCenter;
    IMocksBuilder ctrl;
    @BeforeEach
    public void setUp(){
        ctrl = EasyMock.createStrictControl();
        notifyCenter = EasyMock.partialMockBuilder(NotifyCenter.class).addMockedMethod("getServer","sendNotify").mock(ctrl);

    }
    @Test
    public void A_notifyUsers_should_return(){

    }
}
