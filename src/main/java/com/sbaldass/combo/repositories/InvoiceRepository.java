package com.sbaldass.combo.repositories;

import com.sbaldass.combo.domain.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {
    Invoice findByOrderId(String orderId);
}
