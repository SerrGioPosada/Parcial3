package co.edu.uniquindio.poo.parcial3.model;

public abstract class Person {
    private String id;
    private String name;
    private String phone;
    private String email;

    protected Person(Builder<?> builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public abstract static class Builder<T extends Builder<T>> {
        protected String id;
        protected String name;
        protected String phone;
        protected String email;

        public T withId(String id) {
            this.id = id;
            return self();
        }

        public T withName(String name) {
            this.name = name;
            return self();
        }

        public T withPhone(String phone) {
            this.phone = phone;
            return self();
        }

        public T withEmail(String email) {
            this.email = email;
            return self();
        }

        protected abstract T self();
        public abstract Person build();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}