package com.watts;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PropagationService extends TransactionPropagationServiceGrpc.TransactionPropagationServiceImplBase {
    @Override
    public void sendTransaction(TransactionPayload request, StreamObserver<PropagationResponse> responseObserver) {
        // This method would write to an AQ in a target DB
        // For now we'll just assume that happened and returned happy path error code 0
        System.out.printf("Received Transaction %s with message %s%n",
                request.getSourceTxnId(), request.getMessage());
        int errorCode = 0;
        responseObserver.onNext(PropagationResponse.newBuilder().setErrorCode(errorCode).build());
        responseObserver.onCompleted();
    }
}
