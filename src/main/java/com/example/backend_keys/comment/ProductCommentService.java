package com.example.backend_keys.comment;

import org.springframework.stereotype.Service;

@Service
public class ProductCommentService implements ProductCommentDao {
    private final ProductCommentRepo productCommentRepo;

    public ProductCommentService(ProductCommentRepo productCommentRepo) {
        this.productCommentRepo = productCommentRepo;
    }

    @Override
    public ProductComment addReviews(ProductCommentDto productCommentDto) {
        ProductComment productComment=new ProductComment();
        productComment.setId(productCommentDto.id());
        productComment.setBody(productCommentDto.body());
        productComment.setProduct(productCommentDto.product());
        productComment.setCustomer(productCommentDto.customer());
        productComment.setCreateDate(productCommentDto.createDate());
        productComment.setTitle(productCommentDto.title());
        productComment.setRating(productCommentDto.rating());
        if(productCommentDto.rating() < 1){
            return null;
        }else {
            return productCommentRepo.save(productComment);
        }
    }

    @Override
    public void deleteReviews(Integer id) {
        productCommentRepo.deleteById(id);

    }

    @Override
    public ProductComment updateReviews(ProductCommentDto productCommentDto) {
        ProductComment comment=productCommentRepo.findById(productCommentDto.id()).orElse(null);
        if(comment != null) {
            comment.setTitle(productCommentDto.title());
            comment.setBody(productCommentDto.body());
            comment.setRating(productCommentDto.rating());
            comment = productCommentRepo.save(comment);
        }

        return comment;

    }

  /*  private ProductCommentDto mapToDto(ProductComment productComment) {

        return productComment.stream()
                .map(c -> new ProductCommentDto(
                        c.getId(),
                        c.getProductId(),
                        c.getTitle(),
                        c.getBody(),
                        c.getRating()
                ))
                .findFirst()
                .orElse(null);
    }*/
}
