package org.example.function;

/**
 * @className: BranchHandle
 * @author: xiaomage
 * @date: 2022/12/17
 **/
public interface BranchHandle {

    /**
     * 分支操作
     *
     * @param trueHandle 为true时要进行的操作
     * @param falseHandle 为false时要进行的操作
     * @return void
     **/
    void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);

}
