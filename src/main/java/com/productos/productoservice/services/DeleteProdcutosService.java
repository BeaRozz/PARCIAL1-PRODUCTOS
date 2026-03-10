package com.productos.productoservice.services;

import org.springframework.stereotype.Service;

import com.productos.productoservice.models.Products;
import com.productos.productoservice.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteProdcutosService {
    
    private final ProductosRepository repository;

    public void eliminarProducto(String id) {
        Products producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Producto no encontrado con ID: " + id));
        
        repository.delete(producto);
    }
}
