FROM php:fpm-alpine
RUN apk --update --no-cache add git npm
COPY --from=composer /usr/bin/composer /usr/bin/composer
WORKDIR /var/www
CMD composer install ; npm run dev ; php-fpm
EXPOSE 9000
