import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCarrelloComponent } from './view-carrello.component';

describe('ViewCarrelloComponent', () => {
  let component: ViewCarrelloComponent;
  let fixture: ComponentFixture<ViewCarrelloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCarrelloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCarrelloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
