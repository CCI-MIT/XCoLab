package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.TupleStatus;

import org.xcolab.client.modeling.pojo.IModelOutputChartOrder;
import org.xcolab.client.modeling.pojo.IModelOutputItem;

public class ModelOutputErrorBehavior {

    private final ErrorPolicy policy;
    private final String msg;
    private final TupleStatus status;

    private ModelOutputErrorBehavior(TupleStatus status, ErrorPolicy policy, String msg) {
        this.status = status;
        this.policy = policy;
        this.msg = msg;
    }

    public TupleStatus getStatus() {
        return status;
    }

    public ErrorPolicy getPolicy() {
        return policy;
    }

    public String getMessage() {
        return msg;
    }

    public static ModelOutputErrorBehavior createEmptyBehavior(TupleStatus status) {
        return new ModelOutputErrorBehavior(status,null,null);
    }

    public static ModelOutputErrorBehavior getBehavior(TupleStatus status, IModelOutputChartOrder item) {
        if (status == TupleStatus.OUT_OF_RANGE) {
            String policyName = item.getModelIndexRangePolicy();
            if (policyName == null || policyName.trim().equals("")) {
                return createEmptyBehavior(TupleStatus.OUT_OF_RANGE);
            }
            String msg = item.getModelIndexRangeMessage();
           return new ModelOutputErrorBehavior(TupleStatus.OUT_OF_RANGE,
                   ErrorPolicy.valueOf(policyName), msg);
        } else if (status == TupleStatus.INVALID) {
            String policyName = item.getModelIndexErrorPolicy();
            if (policyName == null || policyName.trim().equals("")) {
                return createEmptyBehavior(TupleStatus.INVALID);
            }
            String msg = item.getModelIndexErrorMessage();
           return new ModelOutputErrorBehavior(TupleStatus.INVALID,
                   ErrorPolicy.valueOf(policyName), msg);

        } else {
            return null;
        }
    }

     public static ModelOutputErrorBehavior getBehavior(TupleStatus status, IModelOutputItem item) {
         switch (status) {
             case OUT_OF_RANGE: {
                 String policyName = item.getModelItemRangePolicy();
                 if (policyName == null || policyName.trim().equals("")) {
                     return createEmptyBehavior(TupleStatus.OUT_OF_RANGE);
                 }
                 String msg = item.getModelItemRangeMessage();
                 return new ModelOutputErrorBehavior(TupleStatus.OUT_OF_RANGE,
                         ErrorPolicy.valueOf(policyName), msg);
             }
             case INVALID: {
                 String policyName = item.getModelItemErrorPolicy();
                 if (policyName == null || policyName.trim().equals("")) {
                     return createEmptyBehavior(TupleStatus.INVALID);
                 }
                 String msg = item.getModelItemErrorMessage();
                 return new ModelOutputErrorBehavior(TupleStatus.INVALID,
                         ErrorPolicy.valueOf(policyName), msg);
             }
             default:
                 return null;
         }
    }
}
