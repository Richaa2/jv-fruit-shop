package core.basesyntax.impl;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitService;
import core.basesyntax.strategy.OperationStrategy;

import java.io.File;
import java.util.List;

public class FruitServiceImpl implements FruitService {
    private FruitDao fruitDao;
    private OperationStrategy operationStrategy;

    public FruitServiceImpl(FruitDao fruitDao, OperationStrategy operationStrategy) {
        this.fruitDao = fruitDao;
        this.operationStrategy = operationStrategy;
    }

    @Override
    public void calculateFruit(List<FruitTransaction> fruits) {
        for (FruitTransaction fruitTransaction : fruits) {
            int fruitQuantity = fruitTransaction.getQuantity();
            int operation = operationStrategy.get(fruitTransaction.getOperation()).getOperation(fruitTransaction.getQuantity());
            int resultQ = fruitQuantity + operation != 0 ? fruitQuantity : -fruitQuantity;
            if (Storage.fruits.get(fruitTransaction.getFruit()) != null) {
                Storage.fruits.put(fruitTransaction.getFruit(),
                        Storage.fruits.get(fruitTransaction.getFruit()) + resultQ);
                System.out.println( fruitTransaction.getFruit() + fruitTransaction.getOperation()+Storage.fruits.get(fruitTransaction.getFruit()));
                continue;
            }
            Storage.fruits.put(fruitTransaction.getFruit(), resultQ);

            System.out.println(Storage.fruits.get(fruitTransaction.getFruit()));
        }
        System.out.println(Storage.fruits);
    }
}

//        List<Fruit> fruitTransactionSet = notSortedList.stream().filter(x -> x.getOperation() == FruitTransaction.Operation.BALANCE
//                        || x.getOperation() == FruitTransaction.Operation.SUPPLY).map(x -> !clearList.contains(x.getFruit()) ? clearList.add(new Fruit(x.getQuantity(),x.getFruit())) : clearList.indexOf());
//                .collect(Collectors.toList());
//        notSortedList.stream().filter(x -> x.getOperation() == FruitTransaction.Operation.BALANCE
//                        || x.getOperation() == FruitTransaction.Operation.SUPPLY)
//
//                .collect(Collectors.toSet());
//        for (int i = 0; i < fruitTransactionSet.size() - 1; i++) {
//            for (int j = 0; j < fruitTransactionSet.size(); j++) {
//                if (fruitTransactionSet.get(i).getFruit().equals(fruitTransactionSet.get(j).getFruit())) {
//                    FruitTransaction fruitTransaction = new FruitTransaction();
//                    fruitTransaction.setQuantity(fruitTransactionSet.get(i).getQuantity() + fruitTransactionSet.get(j).getQuantity());
//                    fruitTransaction.setFruit(fruitTransaction.getFruit());
//                    fruitTransaction.setOperation();
//                    clearList.add(fruitTransactionSet.get(i).setQuantity(fruitTransactionSet.get(i).getQuantity() + fruitTransactionSet.get(j).getQuantity()));
//                }
//            }
//        }

//                        || x.getOperation() == FruitTransaction.Operation.SUPPLY)
//                .map(x -> x.getOperation() == FruitTransaction.Operation.BALANCE
//                        || x.getOperation() == FruitTransaction.Operation.SUPPLY
//                        ? fruitTransactionSet.add(x) : fruitTransactionSet.)

//         notSortedList.stream()
//                .map(x -> x.getOperation() == FruitTransaction.Operation.BALANCE
//                        || x.getOperation() == FruitTransaction.Operation.SUPPLY
//                        ? x.getQuantity() : 0)
//                .collect(Collectors.toList());
//       Set<FruitTransaction> clearList = null;
//        return null;
//    }
//}