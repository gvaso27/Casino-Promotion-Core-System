#Build the Docker Image
docker build -t casino-app .

#Run the Docker Container with Volume Mounting
docker run -v PATH/TO/data:/data casino-app