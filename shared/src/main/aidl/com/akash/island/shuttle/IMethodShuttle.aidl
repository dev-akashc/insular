package com.akash.island.shuttle;

import com.akash.island.shuttle.MethodInvocation;

interface IMethodShuttle {
    void invoke(inout MethodInvocation invocation);
}
