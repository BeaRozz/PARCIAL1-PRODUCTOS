package com.productos.productoservice.services;

import org.springframework.stereotype.Service;

import com.productos.productoservice.dto.ProductKafkaDto;
import com.productos.productoservice.dto.ProductoDto;
import com.productos.productoservice.kafka.ProductProducer;
import com.productos.productoservice.models.Products;
import com.productos.productoservice.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateProductosService {
    
    private final ProductProducer productProducer;
    private final ProductosRepository repository;

    public Products crearProducto(ProductoDto dto) {
        Products nuevoProducto = new Products();
        
        nuevoProducto.setNombre(dto.getNombre());
        nuevoProducto.setDescripcion(dto.getDescripcion());
        nuevoProducto.setPrecio(dto.getPrecio());
        nuevoProducto.setCantidad(dto.getCantidad());
        nuevoProducto.setImagen(dto.getImagen());
        nuevoProducto.setMarca(dto.getMarca());
        nuevoProducto.setProveedor(dto.getProveedor());
        nuevoProducto.setCategoria(dto.getCategoria());
        
        return repository.save(nuevoProducto);
    }

    public Products crearProductoFail(ProductKafkaDto productoFailed) {
        
        // Simulamos un error lanzando una excepción personalizada
        log.error("Error al crear producto [{}]: {}", productoFailed.getId(), "Simulación de error en la creación del producto");
        productProducer.enviarProducto(productoFailed);
        throw new RuntimeException("Error simulado al crear el producto");
    }
}
