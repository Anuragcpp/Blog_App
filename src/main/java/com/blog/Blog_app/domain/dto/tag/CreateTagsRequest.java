package com.blog.Blog_app.domain.dto.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagsRequest {

    @NotBlank(message = "At least one tag Name is required")
    @Size(max = 10, message = "Maximum {max} tags are allowed")
    private Set<
            @Size(min = 2,max = 30, message = "Tag name must be between {min} and {max} characters")
                    @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name can only contains letters,numbers,space,hyphen ")
            String
            > names;
}
