package com.productos.productoservice.services;

import org.springframework.stereotype.Service;

import com.productos.productoservice.dto.ProductoDto;
import com.productos.productoservice.models.Products;
import com.productos.productoservice.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateProductosService {
    
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
}
