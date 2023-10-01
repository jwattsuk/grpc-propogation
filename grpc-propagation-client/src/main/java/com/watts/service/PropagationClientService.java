package com.watts.service;

import com.watts.PropagationResponse;
import com.watts.TransactionPayload;
import com.watts.TransactionPropagationServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class PropagationClientService {

    @GrpcClient("grpc-propagation-client")
    TransactionPropagationServiceGrpc.TransactionPropagationServiceBlockingStub synchronousClient;

    public String sendTransaction(String sourceTxnId, String message) {
        TransactionPayload payload = TransactionPayload.newBuilder()
                .setSourceTxnId(sourceTxnId)
                .setMessage(message)
                .build();

        try {
            PropagationResponse response = synchronousClient.sendTransaction(payload);
            if (response == null || response.getErrorCode() != 0) {
                return String.format("Error propagating Source Txn Id %s", sourceTxnId);
            }
            return String.format("Source Txn Id %s successfully propagated", sourceTxnId);

        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
