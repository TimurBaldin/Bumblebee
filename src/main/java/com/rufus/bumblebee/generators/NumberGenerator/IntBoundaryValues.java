package com.rufus.bumblebee.generators.NumberGenerator;

import com.rufus.bumblebee.container.Container;
import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.Rule;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class : Генерирует числа для проверки "граничных значений"
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class IntBoundaryValues implements Rule {

    private final String TYPE = "NUMERIC";
    private Long MaxInt;
    private Long MinInt;
    private Integer QUANTITY;
    private Container column;
    private List<TypeTestData> values = new LinkedList<TypeTestData>();

    public IntBoundaryValues(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY, Container column) {
        this.MaxInt = BoundaryIntEnd;
        this.MinInt = BoundaryIntStart;
        this.QUANTITY = QUANTITY;
        this.column = column;
    }

    @Override
    public void construct() throws GeneratorExceptionInputOptions, TransferException {
        if (checkRule()) {
            throw new GeneratorExceptionInputOptions("Invalid input conditions.", MaxInt.toString() + MinInt.toString(), QUANTITY.toString());
        } else {
            buildBoundary();
            buildTestNum();
            transfer();
        }
    }

    @Override
    public void transfer() throws TransferException {
        if (column == null || values.size() == 0) {
            throw new TransferException("Value column not be null or test data was not generated.");
        } else {
            column.setValues(values);
        }
    }

    private boolean checkRule() {
        if ((MaxInt < MinInt) || (Math.abs(MaxInt - MinInt) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private Long buildRandNum() {
        Long Num_1;
        if (MinInt >= 0) {
            Num_1 = MinInt + 2;
        } else {
            Num_1 = MinInt - 1;
        }
        return ThreadLocalRandom.current().nextLong(Num_1, MaxInt);
    }

    private void buildBoundary() {
        for (Long j = -1L; j <= 1L; j++) {
            values.add(new BaseDataType(String.valueOf(MinInt - j), TYPE));
            values.add(new BaseDataType(String.valueOf(MaxInt - j), TYPE));

        }

    }

    private void buildTestNum() {
        for (int i = 1; i <= QUANTITY; i++) {
            values.add(new BaseDataType(buildRandNum().toString(), TYPE));
        }
    }

}