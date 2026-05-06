package com.productos.productoservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productos.productoservice.dto.ProductoDto;
import com.productos.productoservice.models.Products;
import com.productos.productoservice.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateProductosService {
    
    private final ProductosRepository repository;

    public Products actualizarProducto(String id, ProductoDto dto) {
        Products productoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar. Producto no encontrado con ID: " + id));

        productoExistente.setNombre(dto.getNombre());
        productoExistente.setDescripcion(dto.getDescripcion());
        productoExistente.setPrecio(dto.getPrecio());
        productoExistente.setCantidad(dto.getCantidad());
        productoExistente.setImagen(dto.getImagen());
        productoExistente.setMarca(dto.getMarca());
        productoExistente.setProveedor(dto.getProveedor());
        productoExistente.setCategoria(dto.getCategoria());

        return repository.save(productoExistente);
    }

    @Transactional
    public void updateStock(String id, int quantityToReduce) {
        Products product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));

        // Restamos sin validar si es menor a cero
        int newStock = product.getCantidad() - quantityToReduce;
        product.setCantidad(newStock);

        repository.save(product);
    }
}
