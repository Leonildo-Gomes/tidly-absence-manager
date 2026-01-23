package no.tidly.core.shared;

import java.beans.PropertyDescriptor;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object object) {
        final BeanWrapper src = new BeanWrapperImpl(object);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = Stream.of(pds)
                .filter(pd -> src.getPropertyValue(pd.getName()) == null)
                .map(pd -> pd.getName())
                .collect(Collectors.toSet());

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);

    }

}
