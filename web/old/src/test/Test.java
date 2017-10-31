import com.teeny.wms.dto.CommonDTO;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see Test
 * @since 2017/8/5
 */

public class Test extends CommonDTO {


    public static void main(String[] args) {
        CommonDTO dto = new Test();
        System.out.println("result = " + dto.getClass());
    }
}
