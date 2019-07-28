package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Container.TestDataContainer;
import com.rufus.bumblebee.Main.Container.Container;
import com.rufus.bumblebee.Main.Generators.LineGenerator.StringBoundaryValues;
import com.rufus.bumblebee.Main.Generators.LineGenerator.StringSpecialValues;
import com.rufus.bumblebee.Main.Generators.NumberGenerator.IntBoundaryValues;
import com.rufus.bumblebee.Main.Generators.NumberGenerator.IntFullRange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestsFactory {

    public TestDataContainer getTestDataContainer(String name) {
        return new TestDataContainer(name);
    }

    public StringBoundaryValues getBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue, TestDataContainer testDataContainer) {
        return new StringBoundaryValues(Len, INCREASE_QUANTITY, Low, Cap, NullValue, testDataContainer);
    }

    public StringSpecialValues getSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, TestDataContainer testDataContainer) {
        return new StringSpecialValues(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL, testDataContainer);
    }

    public IntBoundaryValues getIntBoundaryTest(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY, Container column) {
        return new IntBoundaryValues(BoundaryIntEnd, BoundaryIntStart, QUANTITY, column);
    }

    public IntFullRange getIntFullRange(Long MaxIntVal, Long MinIntVal, Container column) {
        return new IntFullRange(MaxIntVal, MinIntVal, column);
    }

}
