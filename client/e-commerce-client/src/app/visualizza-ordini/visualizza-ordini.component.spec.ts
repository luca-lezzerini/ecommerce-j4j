import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaOrdiniComponent } from './visualizza-ordini.component';

describe('VisualizzaOrdiniComponent', () => {
  let component: VisualizzaOrdiniComponent;
  let fixture: ComponentFixture<VisualizzaOrdiniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisualizzaOrdiniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaOrdiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
