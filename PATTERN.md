# Development Pattern

The following guide will try to document my pattern for building a React based Single Page Application,
utilizing a Spring backed API server using Spring Data REST.  This guide will go into detail for some of the custom
parts or the gotchas I have encountered.  In some cases I may just link to already excellent documentation to get started
with a piece of the pattern. 

## Versions
+ React 16.8+
+ Spring Boot 2.1+
+ Java 11+
+ Node 10+

## Client Side: React

Currently I have chosen React for my path forward in the SPA world.  I have tried the current versions of Angular, 
React and Ember.  I have also read up on the current version of VueJS.  React and VueJS were neck and neck, Angular and 
Ember were behind those two for me, I chose React instead of Vue for no particular reason.

### Build Tools & CLI
#### Yarn

Using the latest version of Yarn as my package manager.

#### Create React App

Utilizing the Create React App starting point for building a React based application.

### Building Components


#### Classes vs Hooks

When I started down this path Hooks were brand new and the pattern recommended for building React components if you were
starting fresh.  Hooks will be the primary pattern used except when classes are needed.  One such case is using the
Error Boundary functionality.
 
### Handling Errors

Handling errors in a React application requires us two take care of two main cases.  Error Boundaries for our UI/render
logic and try/catch for our logic in handler methods.

#### Error Boundaries

An error boundary is a component with a special life cycle hook that allows it to catch ui/render errors from it's
 children.  It can then process those errors and display an appropriate fallback UI.

#### Handler Methods

A handler method is for example `onClick` or `onChange`.  These methods are not processed or caught by an error boundary
and will cause unexpected exceptions or issues if they are not dealt with properly.  As of writing this guide the
accepted way of dealing with this case is doing proper try/catch logic in these methods.

### Routing

When looking for routing solutions for React two major options emerged.  React Router and Reach Router.  When looking
for examples most examples utilized React Router so I chose that as my router of choice.

#### React Router v4
### Testing

Testing components in React is something I currently know very little about. I need to research further and fill out
this section.

#### Unit Testing

Currently thinking Jest.

#### End to End Testing

Not sure what to do here yet.

### Talking to the Server
#### Proxy for Development

During development it's nice to be able to proxy requests to the backend server.  This can be done by adding the
following configuration to your package.json.

```yaml
...
"proxy": "http://localhost:8080",
...
```

#### API (Api.js)

Currently I have built an Api library that allows me to call my Spring Data REST HAL Api.  I have been iterating on this
library to DRY it up and make it reusable for the cases that my application requires.  I have placed this inline of this
guide as I find it fairly important to my pattern

```javascript 1.8
// Add it here
```

### UI Framework

When building a UI I like choosing a framework as I am far from being a CSS Master.  For a long time I have utilized
different versions of the Bootstrap framework.  Bootstrap is usually my go to but lately I have been looking to try 
something new and it just so happens that Material UI is a nice implementation of the Material spec for React.

#### Bootstrap

Utilize the Bootstrap CSS framework for styling the front end application.  There are a lot of libraries that wrap
Bootstrap for React but I prefer to use the framework directly and build my components as needed for my application.

#### Material UI

Work in progress.

### Security

This pattern currently utilizes the OpenId implicit flow pattern using Okta as the service provider.  I know I know the
implicit flow is going away, as of writing this guide implicit flow was still an accepted standard.  I will look to move
away from it as soon as Okta has provided that path.

The pattern relies on Okta handling the authentication and authorization.  Our token passes back the roles(groups) that
a user is a part of.

#### Okta

The OpenId service provider.  Also has a library for easily integrating with React.

## Server Side: Spring Boot
### Build Tools
#### Maven
#### Spring Initializr
### Server
#### Config
### Model
#### Bean Validations
#### Custom Validations
#### Internationalization
### Repository
#### Projections
#### Spring Data Query Methods
#### QueryDSL Integration
### Controller
#### Custom
### Testing
#### Model
#### Repository
#### Controller
### Database
#### Flyway Migrations
### Extras
#### Spring Boot Actuator
#### Logging
### Security
#### Spring Security
#### Okta

## Deployment
### Docker
### Development
### Production

## Additional Tools
### Spring Cloud Config

