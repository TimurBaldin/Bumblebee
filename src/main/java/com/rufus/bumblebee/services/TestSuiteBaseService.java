package com.rufus.bumblebee.services;

import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.generators.LineGenerator.StringBoundaryValues;
import com.rufus.bumblebee.generators.LineGenerator.StringSpecialValues;
import com.rufus.bumblebee.generators.NumberGenerator.IntBoundaryValues;
import com.rufus.bumblebee.generators.NumberGenerator.IntFullRange;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.sun.media.sound.InvalidDataException;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class : Сервис создания тестовых данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class TestSuiteBaseService extends BaseTestService {

    private List<BaseGenerator> generators = new ArrayList<>();
    private ContainerRepository repository;


    @Autowired
    public TestSuiteBaseService(ContainerRepository repository) {
        this.repository = repository;
    }

    public void selectionBoundaryTest(Integer len, Integer increaseQuantity, Boolean low, Boolean cap, Boolean nullValue, Long containerId) throws InvalidDataException {
        generators.add(
                StringBoundaryValues.builder().
                        len(len).
                        increaseQuantity(increaseQuantity).
                        low(low).
                        cap(cap).
                        nullValue(nullValue).
                        containerRef(repository.getContainerById(containerId).getId()).
                        build()
        );

    }

    public void selectionSpecialLinesTest(Integer specialLen, Integer increaseQuantity, Boolean escSpecial, Boolean special, Long containerId) throws InvalidDataException {
        generators.add(
                StringSpecialValues.builder().
                        specialLen(specialLen).
                        increaseQuantity(increaseQuantity).
                        escSpecial(escSpecial).
                        special(special).
                        containerRef(repository.getContainerById(containerId).getId()).
                        build()
        );

    }

    public void selectionIntBoundary(Long boundaryIntEnd, Long boundaryIntStart, Integer quantity, Long containerId) throws InvalidDataException {
        generators.add(
                IntBoundaryValues.builder().
                        boundaryIntEnd(boundaryIntEnd).
                        boundaryIntStart(boundaryIntStart).
                        quantity(quantity).
                        containerRef(repository.getContainerById(containerId).getId()).
                        build()
        );

    }

    public void selectionIntRange(Long maxIntVal, Long minIntVal, Long containerId) throws InvalidDataException {
        generators.add(
                IntFullRange.builder().
                        maxIntVal(maxIntVal).
                        minIntVal(minIntVal).
                        containerRef(repository.getContainerById(containerId).getId()).
                        build()
        );

    }

    public void startGeneratingData() throws TransferException, GeneratorExceptionInputOptions, InvalidInputException {
        startGeneratingData(generators);
    }

}