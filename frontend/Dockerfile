# Base image
FROM node:10.19-slim AS builder

WORKDIR /usr/app/snackbar

COPY package.json ./

RUN npm install

COPY . .

RUN npm run build

# Deploy to nginx
FROM nginx:1.17-alpine AS deploy

COPY --from=builder /usr/app/snackbar/dist/snackbar /usr/share/nginx/html
