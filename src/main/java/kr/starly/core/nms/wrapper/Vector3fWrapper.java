package kr.starly.core.nms.wrapper;

import kr.starly.core.nms.tank.NmsOtherUtil;
import lombok.Getter;

@Getter
public class Vector3fWrapper {

    private final float x;
    private final float y;
    private final float z;

    public Vector3fWrapper(Object vector3f) {
        NmsOtherUtil nmsOtherUtil = NmsOtherUtil.getInstance();

        try {
            x = (float) nmsOtherUtil.Vector3f_getX().invoke(vector3f);
            y = (float) nmsOtherUtil.Vector3f_getY().invoke(vector3f);
            z = (float) nmsOtherUtil.Vector3f_getZ().invoke(vector3f);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Invalid vector3f object");
        }
    }

    public Vector3fWrapper(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Object getAsVector3f() {
        NmsOtherUtil nmsOtherUtil = NmsOtherUtil.getInstance();

        try {
            return nmsOtherUtil.Vector3f_Constructor().newInstance(x, y, z);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
