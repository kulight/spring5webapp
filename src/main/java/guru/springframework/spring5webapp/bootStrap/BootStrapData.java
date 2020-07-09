package guru.springframework.spring5webapp.bootStrap;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AddressRepository;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AddressRepository addressRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, AddressRepository addressRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author michal = new Author("Michal", "Manor");
        Book breath = new Book("Breath", "123456");
        michal.getBooks().add(breath);
        breath.getAuthors().add(michal);

        authorRepository.save(michal);
        bookRepository.save(breath);

        Author lisa = new Author("Lisa", "Kaplan");
        Book kafka = new Book("Kafka101", "987456");
        lisa.getBooks().add(kafka);
        kafka.getAuthors().add(lisa);

        authorRepository.save(lisa);
        bookRepository.save(kafka);

        Publisher publisher = new Publisher("Yotam");
        Address address = new Address("Afikim", "Afikim", "Israel", "1514800");
        publisher.setAddress(address);
        address.setPublisher(publisher);
        kafka.setPublisher(publisher);
        breath.setPublisher(publisher);
        publisher.getBooks().add(kafka);
        publisher.getBooks().add(breath);

        addressRepository.save(address);
        publisherRepository.save(publisher);
        bookRepository.save(breath);
        bookRepository.save(kafka);

        System.out.println("Started data bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher: " + publisher.getName() + " Lives at: " + publisher.getAddress().toString());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());
    }
}
